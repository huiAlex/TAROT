package experiment.preprocess.parsecode.util;

import experiment.preprocess.parsecode.entity.ClassEntity;
import experiment.preprocess.parsecode.entity.FieldEntity;
import experiment.preprocess.parsecode.entity.MethodEntity;
import experiment.preprocess.parsecode.entity.ParameterEntity;
import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

//access the package, class name, attribute, method of the class node.
public class ClassASTVisitor extends ASTVisitor {

    private ClassEntity classEntity;

    private ArrayList<FieldEntity> fieldList;
    private ArrayList<MethodEntity> methodList;

    public ClassASTVisitor() {
        super();
        this.classEntity = new ClassEntity();
        this.fieldList = new ArrayList<>();
        this.methodList = new ArrayList<>();
    }

    // access the package
    @Override
    public boolean visit(PackageDeclaration node) {
        classEntity.setPackageName(node.getName().toString());
        return true;
    }



    // access the enum
    @Override
    public boolean visit(EnumDeclaration node) {
        // skip inner class
        if (!node.isPackageMemberTypeDeclaration()) {
            return true;
        }

        // get the class name
        classEntity.setClassName(node.getName().toString());

        // get the class comment
        if (node.getJavadoc() == null) {
            classEntity.setClassComment("");
        } else {
            classEntity.setClassComment(node.getName().toString() + "  " + node.getJavadoc().toString());
        }

        // get the interface of class implements
        List<String> superInterface = new ArrayList<>();
        List<Type> superInterfaceList = node.superInterfaceTypes();
        if (superInterfaceList == null || superInterfaceList.size() == 0) {
            superInterface.add("");
        } else {
            for (Type superInterfaceType : superInterfaceList) {
                //	if the interface is generic type
                if (superInterfaceType.isParameterizedType()) {
                    ParameterizedType parameterizedType = (ParameterizedType) superInterfaceType;
                    String parameterizedType1 = parameterizedType.getType().toString();
                    String parameterizedType2 = parameterizedType.typeArguments().get(0).toString();
                    superInterface.add(parameterizedType1);
                    superInterface.add(parameterizedType2);
                } else {
                    superInterface.add(superInterfaceType.toString());
                }
            }
        }
        classEntity.setSuperInterface(superInterface);
        return true;
    }

    //	access the class
    @Override
    public boolean visit(TypeDeclaration node) {
        // skip inner class
        if (!node.isPackageMemberTypeDeclaration()) {
            return true;
        }

        //get the class name
        classEntity.setClassName(node.getName().toString());

        // get the class comment
        if (node.getJavadoc() == null) {
            classEntity.setClassComment("");
        } else {
            classEntity.setClassComment(node.getName().toString() + "  " + node.getJavadoc().toString());
        }

        // get the super class
        Type superClassType = node.getSuperclassType();
        if (superClassType == null) {
            classEntity.setSuperClassName("");
        } else {
            // if the super class is generic type
            if (superClassType.isParameterizedType()) {
                ParameterizedType parameterizedType = (ParameterizedType) superClassType;
                String parameterizedType1 = parameterizedType.getType().toString();
                classEntity.setSuperClassName(parameterizedType1);
            } else {
                classEntity.setSuperClassName(superClassType.toString());
            }
        }

        // get the interface of class implements
        List<String> superInterface = new ArrayList<>();
        List<Type> superInterfaceList = node.superInterfaceTypes();
        if (superInterfaceList == null || superInterfaceList.size() == 0) {
            superInterface.add("");
        } else {
            for (Type superInterfaceType : superInterfaceList) {
                // if the interface is generic type
                if (superInterfaceType.isParameterizedType()) {
                    ParameterizedType parameterizedType = (ParameterizedType) superInterfaceType;
                    String parameterizedType1 = parameterizedType.getType().toString();
                    String parameterizedType2 = parameterizedType.typeArguments().get(0).toString();
                    superInterface.add(parameterizedType1);
                    superInterface.add(parameterizedType2);
                } else {
                    superInterface.add(superInterfaceType.toString());
                }
            }
        }
        classEntity.setSuperInterface(superInterface);
        return true;
    }

    // access the annotation type
    @Override
    public boolean visit(AnnotationTypeDeclaration node) {
        // skip inner class
        if (!node.isPackageMemberTypeDeclaration()) {
            return true;
        }

        // get the class name
        classEntity.setClassName(node.getName().toString());

        // get the class comment
        if (node.getJavadoc() == null) {
            classEntity.setClassComment("");
        } else {
            classEntity.setClassComment(node.getName().toString() + "  " + node.getJavadoc().toString());
        }
        return true;
    }


    //	access the attribute
    @Override
    public boolean visit(FieldDeclaration node) {
        for (Object obj : node.fragments()) {
            VariableDeclarationFragment v = (VariableDeclarationFragment) obj;
            Type fieldType = node.getType();
            // if attribute type is generic type
            if (fieldType.isParameterizedType()) {
                ParameterizedType parameterizedType = (ParameterizedType) fieldType;
                String parameterizedType1 = parameterizedType.getType().toString();
                String parameterizedType2 = parameterizedType.typeArguments().get(0).toString();
                FieldEntity fieldEntity1 = new FieldEntity();
                fieldEntity1.setFieldName(v.getName().toString());
                fieldEntity1.setFieldType(parameterizedType1);
                fieldList.add(fieldEntity1);
                FieldEntity fieldEntity2 = new FieldEntity();
                fieldEntity2.setFieldName(v.getName().toString());
                fieldEntity2.setFieldType(parameterizedType2);
                fieldList.add(fieldEntity2);
            } else {
                FieldEntity fieldEntity = new FieldEntity();
                fieldEntity.setFieldName(v.getName().toString());
                fieldEntity.setFieldType(fieldType.toString());
                fieldList.add(fieldEntity);
            }
        }
        return true;
    }

    //	access the method
    @Override
    public boolean visit(MethodDeclaration node) {
        MethodEntity methodEntity = new MethodEntity();
        // get the method name
        methodEntity.setMethodName(node.getName().toString());

        // get method comment
        if (node.getJavadoc() == null) {
            methodEntity.setMethodComment("");
        } else {
            methodEntity.setMethodComment(node.getJavadoc().toString());
        }

        // get method body
        handleMethodBody(methodEntity, node.getBody());

        // get the return type of method
        Type returnType = node.getReturnType2();
        if (returnType == null) {
            methodEntity.setReturnType("");
        } else {
            // if return type is generic type
            if (returnType.isParameterizedType()) {
                ParameterizedType parameterizedType = (ParameterizedType) returnType;
                String parameterizedType2 = parameterizedType.typeArguments().get(0).toString();
                methodEntity.setReturnType(parameterizedType2);
            } else {
                methodEntity.setReturnType(returnType.toString());
            }
        }

        // get the parameters of the method
        List<ParameterEntity> paramters = new ArrayList<>();
        List<SingleVariableDeclaration> parameterList = node.parameters();
        if (parameterList == null || parameterList.size() == 0) {
            paramters.add(null);
        } else {
            for (SingleVariableDeclaration SingleVariableDeclaration : parameterList) {
                Type paramterType = SingleVariableDeclaration.getType();
                String paramName = SingleVariableDeclaration.getName().toString();
                // if the parameter type is generic type
                if (paramterType.isParameterizedType()) {
                    ParameterizedType parameterizedType = (ParameterizedType) paramterType;
                    String parameterizedType2 = parameterizedType.typeArguments().get(0).toString();
                    ParameterEntity parameterEntity = new ParameterEntity();
                    parameterEntity.setParamType(parameterizedType2);
                    parameterEntity.setParamName(paramName);
                    paramters.add(parameterEntity);
                } else {
                    ParameterEntity parameterEntity = new ParameterEntity();
                    parameterEntity.setParamType(paramterType.toString());
                    parameterEntity.setParamName(paramName);
                    paramters.add(parameterEntity);
                }
            }
        }
        methodEntity.setParametersList(paramters);

        // get the exception thrown by method
        List<String> exceptions = new ArrayList<>();
        List<Type> exceptionList = node.thrownExceptionTypes();
        if (exceptionList == null || exceptionList.size() == 0) {
            exceptions.add("");
        } else {
            for (Type type : exceptionList) {
                exceptions.add(type.toString());
            }
        }
        methodEntity.setThrowException(exceptions);
        methodList.add(methodEntity);
        return true;
    }

    // get invoked method
    public void handleMethodBody(MethodEntity methodEntity, Block body) {
        if (body == null)
            return;
        List statements = body.statements();   //get the statements of the method body
        Iterator iter = statements.iterator();
        List<String> innerVarList = new LinkedList<>();
        List<String> innerMethodInvokeList = new LinkedList<>();
        while (iter.hasNext()) {
            //get each statement
            Statement stmt = (Statement) iter.next();
            if (stmt instanceof ExpressionStatement) {
                ExpressionStatement expressStmt = (ExpressionStatement) stmt;
                Expression express = expressStmt.getExpression();

                if (express instanceof Assignment) {
                    Assignment assign = (Assignment) express;
//                    System.out.println("LHS:" + assign.getLeftHandSide() + "; ");
//                    System.out.println("Op:" + assign.getOperator() + "; ");
//                    System.out.println("RHS:" + assign.getRightHandSide());

                } else if (express instanceof MethodInvocation) {
                    MethodInvocation mi = (MethodInvocation) express;
//                    System.out.println("invocation name:" + mi.getName());
//                    System.out.println("invocation exp:" + mi.getExpression());
//                    System.out.println("invocation arg:" + mi.arguments());
//                    System.out.println();
                    innerMethodInvokeList.add(mi.getExpression() + ";" + mi.getName() + ";" + mi.arguments());
                }
//                System.out.println();

            } else if (stmt instanceof VariableDeclarationStatement) {
                VariableDeclarationStatement var = (VariableDeclarationStatement) stmt;
//                System.out.println("Type of variable:" + var.getType());
//                System.out.println("Name of variable:" + var.fragments());
//                System.out.println();
                innerVarList.add(var.getType() + " " + var.fragments());

            } else if (stmt instanceof ReturnStatement) {
                ReturnStatement rtstmt = (ReturnStatement) stmt;
//                System.out.println("return:" + rtstmt.getExpression());
//                System.out.println();
            }
        }

        if (innerMethodInvokeList.size() == 0)
            innerMethodInvokeList.add("");
        if (innerVarList.size() == 0)
            innerVarList.add("");
        methodEntity.setInnerMethodInvokeList(innerMethodInvokeList);
        methodEntity.setInneVarList(innerVarList);
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    public ArrayList<FieldEntity> getFieldList() {
        return fieldList;
    }

    public void setFieldList(ArrayList<FieldEntity> fieldList) {
        this.fieldList = fieldList;
    }

    public ArrayList<MethodEntity> getMethodList() {
        return methodList;
    }

    public void setMethodList(ArrayList<MethodEntity> methodList) {
        this.methodList = methodList;
    }

}
