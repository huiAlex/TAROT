/**
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.codehaus.groovy.classgen.asm.sc;

import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.ForStatement;
import org.codehaus.groovy.classgen.AsmClassGenerator;
import org.codehaus.groovy.classgen.asm.*;
import org.codehaus.groovy.transform.stc.StaticTypeCheckingSupport;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.Enumeration;
import java.util.Iterator;

import static org.objectweb.asm.Opcodes.*;

/**
 * A class to write out the optimized statements
 * @author <a href="mailto:blackdrag@gmx.org">Jochen "blackdrag" Theodorou</a>
 */
public class StaticTypesStatementWriter extends StatementWriter {

    private static final ClassNode ITERABLE_CLASSNODE = ClassHelper.make(Iterable.class);
    private static final ClassNode ENUMERATION_CLASSNODE = ClassHelper.make(Enumeration.class);
    private static final MethodCaller ENUMERATION_NEXT_METHOD = MethodCaller.newInterface(Enumeration.class, "nextElement");
    private static final MethodCaller ENUMERATION_HASMORE_METHOD = MethodCaller.newInterface(Enumeration.class, "hasMoreElements");

    private StaticTypesWriterController controller;

    public StaticTypesStatementWriter(StaticTypesWriterController controller) {
        super(controller);
        this.controller = controller;
    }
    
    @Override
    public void writeBlockStatement(BlockStatement statement) {
        controller.switchToFastPath();
        super.writeBlockStatement(statement);
        controller.switchToSlowPath();
    }

    @Override
    protected void writeForInLoop(final ForStatement loop) {
        controller.getAcg().onLineNumber(loop,"visitForLoop");
        writeStatementLabel(loop);

        CompileStack compileStack = controller.getCompileStack();
        MethodVisitor mv = controller.getMethodVisitor();
        OperandStack operandStack = controller.getOperandStack();

        compileStack.pushLoop(loop.getVariableScope(), loop.getStatementLabels());

        // Identify type of collection
        TypeChooser typeChooser = controller.getTypeChooser();
        Expression collectionExpression = loop.getCollectionExpression();
        ClassNode collectionType = typeChooser.resolveType(collectionExpression, controller.getClassNode());
        Parameter loopVariable = loop.getVariable();
        int size = operandStack.getStackLength();
        if (collectionType.isArray() && loopVariable.getOriginType().equals(collectionType.getComponentType())) {
            writeOptimizedForEachLoop(compileStack, operandStack, mv, loop, collectionExpression, collectionType, loopVariable);
        } else if (ENUMERATION_CLASSNODE.equals(collectionType)) {
            writeEnumerationBasedForEachLoop(compileStack, operandStack, mv, loop, collectionExpression, collectionType, loopVariable);
        } else {
            writeIteratorBasedForEachLoop(compileStack, operandStack, mv, loop, collectionExpression, collectionType, loopVariable);
        }
        operandStack.popDownTo(size);
        compileStack.pop();
    }

    private void writeOptimizedForEachLoop(
            CompileStack compileStack,
            OperandStack operandStack,
            MethodVisitor mv,
            ForStatement loop,
            Expression collectionExpression,
            ClassNode collectionType,
            Parameter loopVariable) {
        BytecodeVariable variable = compileStack.defineVariable(loopVariable, false);

        Label continueLabel = compileStack.getContinueLabel();
        Label breakLabel = compileStack.getBreakLabel();

        AsmClassGenerator acg = controller.getAcg();

        // load array on stack
        collectionExpression.visit(acg);
        mv.visitInsn(DUP);
        int array = compileStack.defineTemporaryVariable("$arr", collectionType, true);
        mv.visitJumpInsn(IFNULL, breakLabel);

        // $len = array.length
        mv.visitVarInsn(ALOAD, array);
        mv.visitInsn(ARRAYLENGTH);
        operandStack.push(ClassHelper.int_TYPE);
        int arrayLen = compileStack.defineTemporaryVariable("$len", ClassHelper.int_TYPE, true);

        // $idx = 0
        mv.visitInsn(ICONST_0);
        operandStack.push(ClassHelper.int_TYPE);
        int loopIdx = compileStack.defineTemporaryVariable("$idx", ClassHelper.int_TYPE, true);

        mv.visitLabel(continueLabel);
        // $idx<$len?
        mv.visitVarInsn(ILOAD, loopIdx);
        mv.visitVarInsn(ILOAD, arrayLen);
        mv.visitJumpInsn(IF_ICMPGE, breakLabel);

        // get array element
        loadFromArray(mv, variable, array, loopIdx);

        // $idx++
        mv.visitIincInsn(loopIdx, 1);

        // loop body
        loop.getLoopBlock().visit(acg);

        mv.visitJumpInsn(GOTO, continueLabel);

        mv.visitLabel(breakLabel);

    }

    private void loadFromArray(MethodVisitor mv, BytecodeVariable variable, int array, int iteratorIdx) {
        OperandStack os = controller.getOperandStack();
        mv.visitVarInsn(ALOAD, array);
        mv.visitVarInsn(ILOAD, iteratorIdx);

        ClassNode varType = variable.getType();
        boolean primitiveType = ClassHelper.isPrimitiveType(varType);
        boolean isByte = ClassHelper.byte_TYPE.equals(varType);
        boolean isShort = ClassHelper.short_TYPE.equals(varType);
        boolean isInt = ClassHelper.int_TYPE.equals(varType);
        boolean isLong = ClassHelper.long_TYPE.equals(varType);
        boolean isFloat = ClassHelper.float_TYPE.equals(varType);
        boolean isDouble = ClassHelper.double_TYPE.equals(varType);
        boolean isChar = ClassHelper.char_TYPE.equals(varType);
        boolean isBoolean = ClassHelper.boolean_TYPE.equals(varType);

        if (primitiveType) {
            if (isByte) {
                mv.visitInsn(BALOAD);
            }
            if (isShort) {
                mv.visitInsn(SALOAD);
            }
            if (isInt || isChar || isBoolean) {
                mv.visitInsn(isChar ? CALOAD : isBoolean ? BALOAD : IALOAD);
            }
            if (isLong) {
                mv.visitInsn(LALOAD);
            }
            if (isFloat) {
                mv.visitInsn(FALOAD);
            }
            if (isDouble) {
                mv.visitInsn(DALOAD);
            }
        } else {
            mv.visitInsn(AALOAD);
        }
        os.push(varType);
        os.storeVar(variable);
    }

    private void writeIteratorBasedForEachLoop(
            CompileStack compileStack,
            OperandStack operandStack,
            MethodVisitor mv,
            ForStatement loop,
            Expression collectionExpression,
            ClassNode collectionType,
            Parameter loopVariable) {
        // Declare the loop counter.
        BytecodeVariable variable = compileStack.defineVariable(loopVariable, false);

        if (StaticTypeCheckingSupport.implementsInterfaceOrIsSubclassOf(collectionType, ITERABLE_CLASSNODE)) {
            MethodCallExpression iterator = new MethodCallExpression(collectionExpression, "iterator", new ArgumentListExpression());
            iterator.setMethodTarget(collectionType.getMethod("iterator", Parameter.EMPTY_ARRAY));
            iterator.setImplicitThis(false);
            iterator.visit(controller.getAcg());
        } else {
            collectionExpression.visit(controller.getAcg());
            mv.visitMethodInsn(INVOKESTATIC, "org/codehaus/groovy/runtime/DefaultGroovyMethods", "iterator", "(Ljava/lang/Object;)Ljava/util/Iterator;", false);
            operandStack.replace(ClassHelper.Iterator_TYPE);
        }

        // Then get the iterator and generate the loop control

        int iteratorIdx = compileStack.defineTemporaryVariable("iterator", ClassHelper.Iterator_TYPE, true);

        Label continueLabel = compileStack.getContinueLabel();
        Label breakLabel = compileStack.getBreakLabel();

        mv.visitLabel(continueLabel);
        mv.visitVarInsn(ALOAD, iteratorIdx);
        writeIteratorHasNext(mv);
        // note: ifeq tests for ==0, a boolean is 0 if it is false
        mv.visitJumpInsn(IFEQ, breakLabel);

        mv.visitVarInsn(ALOAD, iteratorIdx);
        writeIteratorNext(mv);
        operandStack.push(ClassHelper.OBJECT_TYPE);
        operandStack.storeVar(variable);

        // Generate the loop body
        loop.getLoopBlock().visit(controller.getAcg());

        mv.visitJumpInsn(GOTO, continueLabel);
        mv.visitLabel(breakLabel);

    }

    private void writeEnumerationBasedForEachLoop(
            CompileStack compileStack,
            OperandStack operandStack,
            MethodVisitor mv,
            ForStatement loop,
            Expression collectionExpression,
            ClassNode collectionType,
            Parameter loopVariable) {
        // Declare the loop counter.
        BytecodeVariable variable = compileStack.defineVariable(loopVariable, false);

        collectionExpression.visit(controller.getAcg());

        // Then get the iterator and generate the loop control

        int enumIdx = compileStack.defineTemporaryVariable("$enum", ENUMERATION_CLASSNODE, true);

        Label continueLabel = compileStack.getContinueLabel();
        Label breakLabel = compileStack.getBreakLabel();

        mv.visitLabel(continueLabel);
        mv.visitVarInsn(ALOAD, enumIdx);
        ENUMERATION_HASMORE_METHOD.call(mv);
        // note: ifeq tests for ==0, a boolean is 0 if it is false
        mv.visitJumpInsn(IFEQ, breakLabel);

        mv.visitVarInsn(ALOAD, enumIdx);
        ENUMERATION_NEXT_METHOD.call(mv);
        operandStack.push(ClassHelper.OBJECT_TYPE);
        operandStack.storeVar(variable);

        // Generate the loop body
        loop.getLoopBlock().visit(controller.getAcg());

        mv.visitJumpInsn(GOTO, continueLabel);
        mv.visitLabel(breakLabel);

    }

}
