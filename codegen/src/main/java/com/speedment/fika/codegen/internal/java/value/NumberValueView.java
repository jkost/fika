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
package com.speedment.fika.codegen.internal.java.value;

import com.speedment.fika.codegen.Generator;
import com.speedment.fika.codegen.Transform;
import com.speedment.fika.codegen.internal.model.value.NumberValue;
import java.math.BigDecimal;
import java.math.BigInteger;
import static java.util.Objects.requireNonNull;
import java.util.Optional;

/**
 * Transforms from an {@link NumberValue} to java code.
 * 
 * @author Emil Forslund
 */
public final class NumberValueView implements Transform<NumberValue, String> {
    
    /**
     * {@inheritDoc}
     */
	@Override
	public Optional<String> transform(Generator gen, NumberValue model) {
        requireNonNull(gen);
        requireNonNull(model);
        
        final Number num = requireNonNull(model.getValue());
        final StringBuilder result = new StringBuilder();
        
        if (num instanceof Byte) {
            result.append(Byte.toString(num.byteValue()));
        } else if (num instanceof Short) {
            result.append(Short.toString(num.shortValue()));
        } else if (num instanceof Integer) {
            result.append(Integer.toString(num.intValue()));
        } else if (num instanceof Long) {
            result.append(Long.toString(num.longValue())).append("l");
        } else if (num instanceof Float) {
            result.append(Float.toString(num.floatValue())).append("f");
        } else if (num instanceof Double) {
            result.append(Double.toString(num.doubleValue())).append("d");
        } else if (num instanceof BigInteger) {
            result.append("new ");
            
            if (!gen.getDependencyMgr().isLoaded(BigDecimal.class.getName())) {
                result.append("java.math.");
            }
            
            result.append("BigInteger(\"").append(((BigInteger) num).toString()).append("\")");
        } else if (num instanceof BigDecimal) {
            result.append("new ");
            
            if (!gen.getDependencyMgr().isLoaded(BigDecimal.class.getName())) {
                result.append("java.math.");
            }
            
            result.append("BigDecimal(\"").append(((BigDecimal) num).toPlainString()).append("\")");
        } else {
            throw new UnsupportedOperationException(
                "Unknown Number implementation '" + num.getClass().getName() + "'."
            );
        }
        
		return Optional.of(result.toString());
	}
}