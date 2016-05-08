/**
 *
 * Copyright (c) 2006-2016, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.fika.codegen.java;

import com.speedment.fika.codegen.TransformFactory;
import com.speedment.fika.codegen.model.Annotation;
import com.speedment.fika.codegen.model.AnnotationUsage;
import com.speedment.fika.codegen.model.Class;
import com.speedment.fika.codegen.model.Constructor;
import com.speedment.fika.codegen.model.Enum;
import com.speedment.fika.codegen.model.EnumConstant;
import com.speedment.fika.codegen.model.Field;
import com.speedment.fika.codegen.model.File;
import com.speedment.fika.codegen.model.Generic;
import com.speedment.fika.codegen.model.Import;
import com.speedment.fika.codegen.model.Initializer;
import com.speedment.fika.codegen.model.Interface;
import com.speedment.fika.codegen.model.InterfaceField;
import com.speedment.fika.codegen.model.InterfaceMethod;
import com.speedment.fika.codegen.model.Javadoc;
import com.speedment.fika.codegen.model.JavadocTag;
import com.speedment.fika.codegen.model.Method;
import com.speedment.fika.codegen.model.Type;
import com.speedment.fika.codegen.model.modifier.Modifier;
import com.speedment.fika.codegen.internal.DefaultTransformFactory;
import com.speedment.fika.codegen.internal.java.AnnotationUsageView;
import com.speedment.fika.codegen.internal.java.AnnotationView;
import com.speedment.fika.codegen.internal.java.ClassView;
import com.speedment.fika.codegen.internal.java.ConstructorView;
import com.speedment.fika.codegen.internal.java.EnumConstantView;
import com.speedment.fika.codegen.internal.java.EnumView;
import com.speedment.fika.codegen.internal.java.FieldView;
import com.speedment.fika.codegen.internal.java.FileView;
import com.speedment.fika.codegen.internal.java.GenericView;
import com.speedment.fika.codegen.internal.java.ImportView;
import com.speedment.fika.codegen.internal.java.InitalizerView;
import com.speedment.fika.codegen.internal.java.InterfaceFieldView;
import com.speedment.fika.codegen.internal.java.InterfaceMethodView;
import com.speedment.fika.codegen.internal.java.InterfaceView;
import com.speedment.fika.codegen.internal.java.JavadocTagView;
import com.speedment.fika.codegen.internal.java.JavadocView;
import com.speedment.fika.codegen.internal.java.MethodView;
import com.speedment.fika.codegen.internal.java.ModifierView;
import com.speedment.fika.codegen.internal.java.TypeView;
import com.speedment.fika.codegen.internal.java.value.ArrayValueView;
import com.speedment.fika.codegen.internal.java.value.BooleanValueView;
import com.speedment.fika.codegen.internal.java.value.EnumValueView;
import com.speedment.fika.codegen.internal.java.value.NumberValueView;
import com.speedment.fika.codegen.internal.java.value.ReferenceValueView;
import com.speedment.fika.codegen.internal.java.value.TextValueView;
import com.speedment.fika.codegen.internal.model.value.ArrayValue;
import com.speedment.fika.codegen.internal.model.value.BooleanValue;
import com.speedment.fika.codegen.internal.model.value.EnumValue;
import com.speedment.fika.codegen.internal.model.value.NumberValue;
import com.speedment.fika.codegen.internal.model.value.ReferenceValue;
import com.speedment.fika.codegen.internal.model.value.TextValue;

/**
 * Implementation of the {@link TransformFactory} interface that comes with
 * all the basic concepts of the Java language preinstalled.
 * 
 * @author Emil Forslund
 * @see    TransformFactory
 */
public class JavaTransformFactory extends DefaultTransformFactory {
    
    /**
     * Instantiates the JavaTransformFactory with a default name.
     */
    public JavaTransformFactory() {
        this("JavaTransformFactory");
    }
    
    /**
     * Instantiates the JavaTransformFactory with a custom name.
     * 
     * @param name  the custom name
     */
    public JavaTransformFactory(String name) {
        super(name);
        
        install(Class.class, ClassView.class);
        install(Interface.class, InterfaceView.class);
        install(Method.class, MethodView.class);
        install(Field.class, FieldView.class);
		install(Type.class, TypeView.class);
		install(Modifier.class, ModifierView.class);
		install(Javadoc.class, JavadocView.class);
		install(JavadocTag.class, JavadocTagView.class);
		install(Import.class, ImportView.class);
		install(Generic.class, GenericView.class);
		install(Enum.class, EnumView.class);
		install(EnumConstant.class, EnumConstantView.class);
		install(Annotation.class, AnnotationView.class);
		install(AnnotationUsage.class, AnnotationUsageView.class);
		install(ArrayValue.class, ArrayValueView.class);
		install(BooleanValue.class, BooleanValueView.class);
		install(EnumValue.class, EnumValueView.class);
		install(NumberValue.class, NumberValueView.class);
		install(ReferenceValue.class, ReferenceValueView.class);
		install(TextValue.class, TextValueView.class);
		install(InterfaceMethod.class, InterfaceMethodView.class);
		install(InterfaceField.class, InterfaceFieldView.class);
		install(Constructor.class, ConstructorView.class);
		install(File.class, FileView.class);
        install(Initializer.class, InitalizerView.class);
    }
}