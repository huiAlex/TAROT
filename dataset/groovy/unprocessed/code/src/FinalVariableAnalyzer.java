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
package org.codehaus.groovy.classgen;

import org.codehaus.groovy.ast.ClassCodeVisitorSupport;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.expr.BinaryExpression;
import org.codehaus.groovy.ast.expr.ClosureExpression;
import org.codehaus.groovy.ast.expr.DeclarationExpression;
import org.codehaus.groovy.ast.expr.EmptyExpression;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.expr.PostfixExpression;
import org.codehaus.groovy.ast.expr.PrefixExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.ast.stmt.CatchStatement;
import org.codehaus.groovy.ast.stmt.EmptyStatement;
import org.codehaus.groovy.ast.stmt.IfStatement;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.stmt.TryCatchStatement;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.transform.stc.StaticTypeCheckingSupport;

import java.lang.reflect.Modifier;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class FinalVariableAnalyzer extends ClassCodeVisitorSupport {

    private final SourceUnit sourceUnit;
    private final VariableNotFinalCallback callback;

    private Set<VariableExpression> declaredFinalVariables = null;
    private boolean inAssignment = false;

    private static enum VariableState {
        is_uninitialized(false),
        is_final(true),
        is_var(false);

        private final boolean isFinal;

        VariableState(final boolean isFinal) {
            this.isFinal = isFinal;
        }


        public VariableState getNext() {
            switch (this) {
                case is_uninitialized:
                    return is_final;
                default:
                    return is_var;
            }
        }

        public boolean isFinal() {
            return isFinal;
        }
    }

    private final Deque<Map<Variable, VariableState>> assignmentTracker = new LinkedList<Map<Variable, VariableState>>();

    public FinalVariableAnalyzer(final SourceUnit sourceUnit) {
        this(sourceUnit, null);
    }

    public FinalVariableAnalyzer(final SourceUnit sourceUnit, final VariableNotFinalCallback callback) {
        this.callback = callback;
        this.sourceUnit = sourceUnit;
        pushState();
    }

    private Map<Variable, VariableState> pushState() {
        HashMap<Variable, VariableState> state = new StateMap();
        assignmentTracker.add(state);
        return state;
    }

    private static Variable getTarget(Variable v) {
        if (v instanceof VariableExpression) {
            Variable t = ((VariableExpression) v).getAccessedVariable();
            if (t==v) return t;
            return getTarget(t);
        }
        return v;
    }
    private Map<Variable, VariableState> popState() {
        return assignmentTracker.removeLast();
    }

    private Map<Variable, VariableState> getState() {
        return assignmentTracker.getLast();
    }

    @Override
    protected SourceUnit getSourceUnit() {
        return sourceUnit;
    }

    public boolean isEffectivelyFinal(Variable v) {
        VariableState state = getState().get(v);
        return (v instanceof Parameter && state == null)
                || (state != null && state.isFinal());
    }

    @Override
    public void visitBlockStatement(final BlockStatement block) {
        Set<VariableExpression> old = declaredFinalVariables;
        declaredFinalVariables = new HashSet<VariableExpression>();
        super.visitBlockStatement(block);
        if (callback!=null) {
            Map<Variable, VariableState> state = getState();
            for (VariableExpression declaredFinalVariable : declaredFinalVariables) {
                VariableState variableState = state.get(declaredFinalVariable.getAccessedVariable());
                if (variableState == null || variableState != VariableState.is_final) {
                    callback.variableNotAlwaysInitialized(declaredFinalVariable);
                }
            }
        }
        declaredFinalVariables = old;
    }

    @Override
    public void visitBinaryExpression(final BinaryExpression expression) {
        boolean assignment = StaticTypeCheckingSupport.isAssignment(expression.getOperation().getType());
        boolean isDeclaration = expression instanceof DeclarationExpression;
        Expression leftExpression = expression.getLeftExpression();
        Expression rightExpression = expression.getRightExpression();
        if (isDeclaration && leftExpression instanceof VariableExpression) {
            VariableExpression var = (VariableExpression) leftExpression;
            if (Modifier.isFinal(var.getModifiers())) {
                declaredFinalVariables.add(var);
            }
        }
        leftExpression.visit(this);
        inAssignment = assignment;
        rightExpression.visit(this);
        inAssignment = false;
        if (assignment) {
            if (leftExpression instanceof Variable) {
                boolean uninitialized =
                        isDeclaration &&
                                rightExpression == EmptyExpression.INSTANCE;
                recordAssignment((Variable) leftExpression, isDeclaration, uninitialized, false, expression);
            }
        }
    }

    @Override
    public void visitClosureExpression(final ClosureExpression expression) {
        boolean old = inAssignment;
        inAssignment = false;
        super.visitClosureExpression(expression);
        inAssignment = old;
    }

    @Override
    public void visitPrefixExpression(final PrefixExpression expression) {
        inAssignment = expression.getExpression() instanceof VariableExpression;
        super.visitPrefixExpression(expression);
        inAssignment = false;
        checkPrePostfixOperation(expression.getExpression(), expression);
    }

    @Override
    public void visitPostfixExpression(final PostfixExpression expression) {
        inAssignment = expression.getExpression() instanceof VariableExpression;
        super.visitPostfixExpression(expression);
        inAssignment = false;
        checkPrePostfixOperation(expression.getExpression(), expression);
    }

    private void checkPrePostfixOperation(final Expression variable, final Expression originalExpression) {
        if (variable instanceof Variable) {
            recordAssignment((Variable) variable, false, false, true, originalExpression);
            if (variable instanceof VariableExpression) {
                Variable accessed = ((VariableExpression) variable).getAccessedVariable();
                if (accessed != variable) {
                    recordAssignment(accessed, false, false, true, originalExpression);
                }
            }
        }
    }

    @Override
    public void visitVariableExpression(final VariableExpression expression) {
        super.visitVariableExpression(expression);
        if (inAssignment) {
            Map<Variable, VariableState> state = getState();
            Variable key = expression.getAccessedVariable();
            VariableState variableState = state.get(key);
            if (variableState==VariableState.is_uninitialized) {
                variableState = VariableState.is_var;
                state.put(key, variableState);
            }
        }
    }

    @Override
    public void visitIfElse(final IfStatement ifElse) {
        visitStatement(ifElse);
        ifElse.getBooleanExpression().visit(this);
        Map<Variable, VariableState> ifState = pushState();
        ifElse.getIfBlock().visit(this);
        popState();
        Statement elseBlock = ifElse.getElseBlock();
        Map<Variable, VariableState> elseState = pushState();
        if (elseBlock instanceof EmptyStatement) {
            // dispatching to EmptyStatement will not call back visitor,
            // must call our visitEmptyStatement explicitly
            visitEmptyStatement((EmptyStatement) elseBlock);
        } else {
            elseBlock.visit(this);
        }
        popState();

        // merge if/else branches
        Map<Variable, VariableState> curState = getState();
        Set<Variable> allVars = new HashSet<Variable>();
        allVars.addAll(curState.keySet());
        allVars.addAll(ifState.keySet());
        allVars.addAll(elseState.keySet());
        for (Variable var : allVars) {
            VariableState beforeValue = curState.get(var);
            VariableState ifValue = ifState.get(var);
            VariableState elseValue = elseState.get(var);
            // merge if and else values
            VariableState mergedIfElse ;
            mergedIfElse = ifValue!=null
                        && elseValue!=null
                        && ifValue.isFinal && elseValue.isFinal?VariableState.is_final:VariableState.is_var;
            if (beforeValue == null) {
                curState.put(var, mergedIfElse);
            } else {
                if (beforeValue == VariableState.is_uninitialized) {
                    curState.put(var, mergedIfElse);
                } else if (ifValue!=null || elseValue!=null) {
                    curState.put(var, VariableState.is_var);
                }
            }
        }
    }

    @Override
    public void visitTryCatchFinally(final TryCatchStatement statement) {
        visitStatement(statement);
        Map<Variable, VariableState> beforeTryCatch = new HashMap<Variable, VariableState>(getState());
        statement.getTryStatement().visit(this);
        for (CatchStatement catchStatement : statement.getCatchStatements()) {
            catchStatement.visit(this);
        }
        Statement finallyStatement = statement.getFinallyStatement();
        // we need to recall which final variables are unassigned so cloning the current state
        Map<Variable, VariableState> afterTryCatchState = new HashMap<Variable, VariableState>(getState());
        if (finallyStatement instanceof EmptyStatement) {
            // dispatching to EmptyStatement will not call back visitor,
            // must call our visitEmptyStatement explicitly
            visitEmptyStatement((EmptyStatement) finallyStatement);
        } else {
            finallyStatement.visit(this);
        }
        // and now we must reset to uninitialized state variables which were only initialized during try/catch
        Map<Variable, VariableState> afterFinally = new HashMap<Variable, VariableState>(getState());
        for (Map.Entry<Variable, VariableState> entry : afterFinally.entrySet()) {
            Variable var = entry.getKey();
            VariableState afterFinallyState = entry.getValue();
            VariableState beforeTryCatchState = beforeTryCatch.get(var);
            if (afterFinallyState==VariableState.is_final
                    && beforeTryCatchState !=VariableState.is_final
                    && afterTryCatchState.get(var)!=beforeTryCatchState) {
                getState().put(var, beforeTryCatchState==null?VariableState.is_uninitialized:beforeTryCatchState);
            }
        }
    }

    private void recordAssignment(
            Variable var,
            boolean isDeclaration,
            boolean uninitialized,
            boolean forceVariable,
            Expression expression) {
        if (var == null) {
            return;
        }
        if (!isDeclaration && var.isClosureSharedVariable()) {
            getState().put(var, VariableState.is_var);
        }
        VariableState variableState = getState().get(var);
        if (variableState == null) {
            variableState = uninitialized ? VariableState.is_uninitialized : VariableState.is_final;
            if (getTarget(var) instanceof Parameter) {
                variableState = VariableState.is_var;
            }
        } else {
            variableState = variableState.getNext();
        }
        if (forceVariable) {
            variableState = VariableState.is_var;
        }
        getState().put(var, variableState);
        if (variableState == VariableState.is_var && callback != null) {
            callback.variableNotFinal(var, expression);
        }
    }

    public static interface VariableNotFinalCallback {
        /**
         * Callback called whenever an assignment transforms an effectively final variable into a non final variable
         * (aka, breaks the "final" modifier contract)
         *
         * @param var  the variable detected as not final
         * @param bexp the expression responsible for the contract to be broken
         */
        void variableNotFinal(Variable var, Expression bexp);

        /**
         * Callback used whenever a variable is declared as final, but can remain in an uninitialized state
         * @param var the variable detected as potentially uninitialized
         */
        void variableNotAlwaysInitialized(VariableExpression var);
    }

    private static class StateMap extends HashMap<Variable, VariableState> {
        @Override
        public VariableState get(final Object key) {
            return super.get(getTarget((Variable)key));
        }

        @Override
        public VariableState put(final Variable key, final VariableState value) {
            return super.put(getTarget(key), value);
        }
    }
}
