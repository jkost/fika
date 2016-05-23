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
package com.speedment.fika.codegen.internal.java;

import com.speedment.fika.codegen.Generator;
import com.speedment.fika.codegen.Transform;
import com.speedment.fika.codegen.model.Field;
import com.speedment.fika.codegen.internal.java.trait.HasAnnotationUsageView;
import com.speedment.fika.codegen.internal.java.trait.HasJavadocView;
import com.speedment.fika.codegen.internal.java.trait.HasModifiersView;
import com.speedment.fika.codegen.internal.java.trait.HasNameView;
import com.speedment.fika.codegen.internal.java.trait.HasTypeView;
import com.speedment.fika.codegen.internal.java.trait.HasValueView;
import static java.util.Objects.requireNonNull;
import java.util.Optional;

/**
 * Transforms from a {@link Field} to java code.
 * 
 * @author Emil Forslund
 */
public final class FieldView implements Transform<Field, String>, 
        HasNameView<Field>, 
        HasJavadocView<Field>, 
        HasModifiersView<Field>, 
        HasTypeView<Field>,
        HasValueView<Field>, 
        HasAnnotationUsageView<Field> {

    /**
     * {@inheritDoc}
     */
	@Override
	public Optional<String> transform(Generator gen, Field model) {
        requireNonNull(gen);
        requireNonNull(model);
        
		return Optional.of(
			renderJavadoc(gen, model) +
            renderAnnotationsInline(gen, model) +
			renderModifiers(gen, model) +
			renderType(gen, model) +
			renderName(gen, model) +
			renderValue(gen, model)
		);
	}
	
}