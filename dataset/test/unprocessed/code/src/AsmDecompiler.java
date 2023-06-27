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
package org.codehaus.groovy.ast.decompiled;

import org.objectweb.asm.*;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.*;

/**
 * A utility class responsible for decompiling JVM class files and producing {@link ClassStub} objects reflecting their structure.
 *
 * @author Peter Gromov
 */
public abstract class AsmDecompiler {

    private static class StubCache {
        /**
         * Caches stubs per URL. This cache is useful when performing multiple compilations in the same JVM/class loader and in tests.
         *
         * It's synchronized "just in case". Occasional misses are expected if several threads attempt to load the same class,
         * but this shouldn't result in serious memory issues.
         */
        static Map<URL, SoftReference<ClassStub>> map = Collections.synchronizedMap(new HashMap<URL, SoftReference<ClassStub>>());
    }

    /**
     * Loads the URL contents and parses them with ASM, producing a {@link ClassStub} object representing the structure of
     * the corresponding class file. Stubs are cached and reused if queried several times with equal URLs.
     *
     * @param url an URL from a class loader, most likely a file system file or a JAR entry.
     * @return the class stub
     * @throws IOException if reading from this URL is impossible
     */
    public static ClassStub parseClass(URL url) throws IOException {
        SoftReference<ClassStub> ref = StubCache.map.get(url);
        ClassStub stub = ref == null ? null : ref.get();
        if (stub == null) {
            DecompilingVisitor visitor = new DecompilingVisitor();
            InputStream stream = url.openStream();
            try {
                new ClassReader(new BufferedInputStream(stream)).accept(visitor, ClassReader.SKIP_FRAMES);
            } finally {
                stream.close();
            }
            stub = visitor.result;
            StubCache.map.put(url, new SoftReference<ClassStub>(stub));
        }
        return stub;
    }

    private static class DecompilingVisitor extends ClassVisitor {
        private static final String[] EMPTY_STRING_ARRAY = new String[0];
        private ClassStub result;

        public DecompilingVisitor() {
            super(Opcodes.ASM5);
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaceNames) {
            result = new ClassStub(fromInternalName(name), access, signature, superName, interfaceNames);
        }

        @Override
        public void visitInnerClass(String name, String outerName, String innerName, int access) {
            result.innerClassModifiers.put(innerName, access);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (!"<clinit>".equals(name)) {
                final MethodStub stub = new MethodStub(name, access, desc, signature, exceptions != null ? exceptions : EMPTY_STRING_ARRAY);
                if (result.methods == null) result.methods = new ArrayList<MethodStub>(1);
                result.methods.add(stub);
                return new MethodVisitor(api) {
                    @Override
                    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                        return readAnnotationMembers(stub.addAnnotation(desc));
                    }

                    @Override
                    public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
                        if (stub.parameterAnnotations == null) stub.parameterAnnotations = new HashMap<Integer, List<AnnotationStub>>(1);
                        List<AnnotationStub> list = stub.parameterAnnotations.get(parameter);
                        if (list == null) {
                            stub.parameterAnnotations.put(parameter, list = new ArrayList<AnnotationStub>());
                        }
                        AnnotationStub annotationStub = new AnnotationStub(desc);
                        list.add(annotationStub);
                        return readAnnotationMembers(annotationStub);
                    }

                    @Override
                    public AnnotationVisitor visitAnnotationDefault() {
                        return new AnnotationReader() {
                            @Override
                            void visitAttribute(String name, Object value) {
                                stub.annotationDefault = value;
                            }
                        };
                    }
                };
            }
            return null;
        }

        @Override
        public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
            return readAnnotationMembers(result.addAnnotation(desc));
        }

        @Override
        public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
            final FieldStub stub = new FieldStub(name, access, desc, signature);
            if (result.fields == null) result.fields = new ArrayList<FieldStub>(1);
            result.fields.add(stub);
            return new FieldVisitor(api) {
                @Override
                public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                    return readAnnotationMembers(stub.addAnnotation(desc));
                }
            };
        }
    }

    private static AnnotationReader readAnnotationMembers(final AnnotationStub stub) {
        return new AnnotationReader() {
            @Override
            void visitAttribute(String name, Object value) {
                stub.members.put(name, value);
            }
        };
    }

    static String fromInternalName(String name) {
        return name.replace('/', '.');
    }

    private static abstract class AnnotationReader extends AnnotationVisitor {
        public AnnotationReader() {
            super(Opcodes.ASM5);
        }

        abstract void visitAttribute(String name, Object value);

        @Override
        public void visit(String name, Object value) {
            visitAttribute(name, value instanceof Type ? new TypeWrapper(((Type) value).getDescriptor()) : value);
        }

        @Override
        public void visitEnum(String name, String desc, String value) {
            visitAttribute(name, new EnumConstantWrapper(desc, value));
        }

        @Override
        public AnnotationVisitor visitAnnotation(String name, String desc) {
            AnnotationStub stub = new AnnotationStub(desc);
            visitAttribute(name, stub);
            return readAnnotationMembers(stub);
        }

        @Override
        public AnnotationVisitor visitArray(String name) {
            final ArrayList<Object> list = new ArrayList<Object>();
            visitAttribute(name, list);
            return new AnnotationReader() {
                @Override
                void visitAttribute(String name, Object value) {
                    list.add(value);
                }
            };
        }

    }
}

