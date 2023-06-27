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
package groovy.transform.builder;

import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassHelper;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.expr.Expression;
import org.codehaus.groovy.ast.stmt.BlockStatement;
import org.codehaus.groovy.transform.AbstractASTTransformation;
import org.codehaus.groovy.transform.BuilderASTTransformation;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import static org.codehaus.groovy.ast.tools.GeneralUtils.assignX;
import static org.codehaus.groovy.ast.tools.GeneralUtils.block;
import static org.codehaus.groovy.ast.tools.GeneralUtils.constX;
import static org.codehaus.groovy.ast.tools.GeneralUtils.ctorX;
import static org.codehaus.groovy.ast.tools.GeneralUtils.declS;
import static org.codehaus.groovy.ast.tools.GeneralUtils.param;
import static org.codehaus.groovy.ast.tools.GeneralUtils.params;
import static org.codehaus.groovy.ast.tools.GeneralUtils.propX;
import static org.codehaus.groovy.ast.tools.GeneralUtils.returnS;
import static org.codehaus.groovy.ast.tools.GeneralUtils.stmt;
import static org.codehaus.groovy.ast.tools.GeneralUtils.varX;
import static org.codehaus.groovy.ast.tools.GenericsUtils.newClass;
import static org.codehaus.groovy.transform.BuilderASTTransformation.MY_TYPE_NAME;
import static org.codehaus.groovy.transform.BuilderASTTransformation.NO_EXCEPTIONS;
import static org.codehaus.groovy.transform.BuilderASTTransformation.NO_PARAMS;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_PRIVATE;

/**
 * This strategy is used with the {@link Builder} AST transform to populate a builder helper class
 * so that it can be used for the fluent creation of instances of a specified class.&nbsp;The specified class is not modified in any way and may be a Java class.
 *
 * You use it by creating and annotating an explicit builder class which will be filled in by during
 * annotation processing with the appropriate build method and setters. An example is shown here:
 * <pre>
 * import groovy.transform.builder.*
 *
 * class Person {
 *     String firstName
 *     String lastName
 * }
 *
 * {@code @Builder}(builderStrategy=ExternalStrategy, forClass=Person)
 * class PersonBuilder { }
 *
 * def person = new PersonBuilder().firstName("Robert").lastName("Lewandowski").build()
 * assert person.firstName == "Robert"
 * assert person.lastName == "Lewandowski"
 * </pre>
 * The {@code prefix} annotation attribute, which defaults to the empty String for this strategy, can be used to create setters with a different naming convention, e.g. with
 * the {@code prefix} changed to 'set', you would use your setters as follows:
 * <pre>
 * def p1 = new PersonBuilder().setFirstName("Robert").setLastName("Lewandowski").setAge(21).build()
 * </pre>
 * or using a prefix of 'with':
 * <pre>
 * def p2 = new PersonBuilder().withFirstName("Robert").withLastName("Lewandowski").withAge(21).build()
 * </pre>
 *
 * The properties to use can be filtered using either the 'includes' or 'excludes' annotation attributes for {@code @Builder}.
 * The {@code @Builder} 'buildMethodName' annotation attribute can be used for configuring the build method's name, default "build".
 *
 * The {@code @Builder} 'builderMethodName' and 'builderClassName' annotation attributes aren't applicable for this strategy.
 * The {@code @Builder} 'useSetters' annotation attribute is ignored by this strategy which always uses setters.
 *
 * @author Marcin Grzejszczak
 * @author Paul King
 */
public class ExternalStrategy extends BuilderASTTransformation.AbstractBuilderStrategy {
    private static final Expression DEFAULT_INITIAL_VALUE = null;

    public void build(BuilderASTTransformation transform, AnnotatedNode annotatedNode, AnnotationNode anno) {
        if (!(annotatedNode instanceof ClassNode)) {
            transform.addError("Error during " + BuilderASTTransformation.MY_TYPE_NAME + " processing: building for " +
                    annotatedNode.getClass().getSimpleName() + " not supported by " + getClass().getSimpleName(), annotatedNode);
            return;
        }
        ClassNode builder = (ClassNode) annotatedNode;
        String prefix = transform.getMemberStringValue(anno, "prefix", "");
        ClassNode buildee = transform.getMemberClassValue(anno, "forClass");
        if (buildee == null) {
            transform.addError("Error during " + MY_TYPE_NAME + " processing: 'forClass' must be specified for " + getClass().getName(), anno);
            return;
        }
        List<String> excludes = new ArrayList<String>();
        List<String> includes = new ArrayList<String>();
        if (!getIncludeExclude(transform, anno, buildee, excludes, includes)) return;
        if (unsupportedAttribute(transform, anno, "builderClassName")) return;
        if (unsupportedAttribute(transform, anno, "builderMethodName")) return;
        List<PropertyInfo> props;
        if (buildee.getModule() == null) {
            props = getPropertyInfoFromBeanInfo(buildee, includes, excludes);
        } else {
            props = getPropertyInfoFromClassNode(buildee, includes, excludes);
        }
        for (String name : includes) {
            checkKnownProperty(transform, anno, name, props);
        }
        for (PropertyInfo prop : props) {
            builder.addField(createFieldCopy(builder, prop));
            builder.addMethod(createBuilderMethodForField(builder, prop, prefix));
        }
        builder.addMethod(createBuildMethod(transform, anno, buildee, props));
    }

    private static MethodNode createBuildMethod(BuilderASTTransformation transform, AnnotationNode anno, ClassNode sourceClass, List<PropertyInfo> fields) {
        String buildMethodName = transform.getMemberStringValue(anno, "buildMethodName", "build");
        final BlockStatement body = new BlockStatement();
        Expression sourceClassInstance = initializeInstance(sourceClass, fields, body);
        body.addStatement(returnS(sourceClassInstance));
        return new MethodNode(buildMethodName, ACC_PUBLIC, sourceClass, NO_PARAMS, NO_EXCEPTIONS, body);
    }

    private MethodNode createBuilderMethodForField(ClassNode builderClass, PropertyInfo prop, String prefix) {
        String propName = prop.getName().equals("class") ? "clazz" : prop.getName();
        String setterName = getSetterName(prefix, prop.getName());
        return new MethodNode(setterName, ACC_PUBLIC, newClass(builderClass), params(param(newClass(prop.getType()), propName)), NO_EXCEPTIONS, block(
                stmt(assignX(propX(varX("this"), constX(propName)), varX(propName))),
                returnS(varX("this", newClass(builderClass)))
        ));
    }

    private static FieldNode createFieldCopy(ClassNode builderClass, PropertyInfo prop) {
        String propName = prop.getName();
        return new FieldNode(propName.equals("class") ? "clazz" : propName, ACC_PRIVATE, newClass(prop.getType()), builderClass, DEFAULT_INITIAL_VALUE);
    }

    public static List<PropertyInfo> getPropertyInfoFromBeanInfo(ClassNode cNode, List<String> includes, List<String> excludes) {
        final List<PropertyInfo> result = new ArrayList<PropertyInfo>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(cNode.getTypeClass());
            for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
                if (AbstractASTTransformation.shouldSkip(descriptor.getName(), excludes, includes)) continue;
                // skip hidden and read-only props
                if (descriptor.isHidden() || descriptor.getWriteMethod() == null) continue;
                result.add(new PropertyInfo(descriptor.getName(), ClassHelper.make(descriptor.getPropertyType())));
            }
        } catch (IntrospectionException ignore) {
        }
        return result;
    }

    private static Expression initializeInstance(ClassNode sourceClass, List<PropertyInfo> props, BlockStatement body) {
        Expression instance = varX("_the" + sourceClass.getNameWithoutPackage(), sourceClass);
        body.addStatement(declS(instance, ctorX(sourceClass)));
        for (PropertyInfo prop : props) {
            body.addStatement(stmt(assignX(propX(instance, prop.getName()), varX(prop.getName().equals("class") ? "clazz" : prop.getName(), newClass(prop.getType())))));
        }
        return instance;
    }

}
