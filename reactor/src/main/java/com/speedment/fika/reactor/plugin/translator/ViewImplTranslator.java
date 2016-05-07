package com.speedment.fika.reactor.plugin.translator;

import com.speedment.Speedment;
import com.speedment.codegen.Generator;
import com.speedment.codegen.model.File;
import com.speedment.codegen.model.Generic;
import com.speedment.codegen.model.Class;
import com.speedment.codegen.model.Constructor;
import com.speedment.codegen.model.Type;
import com.speedment.config.db.Table;
import com.speedment.fika.reactor.MaterializedViewImpl;
import com.speedment.internal.core.code.DefaultJavaClassTranslator;
import static com.speedment.fika.reactor.plugin.translator.TranslatorUtil.mergingColumnType;

/**
 *
 * @author Emil Forslund
 * @since  1.1.0
 */
public final class ViewImplTranslator extends DefaultJavaClassTranslator<Table, Class> {

    public ViewImplTranslator(
            Speedment speedment, 
            Generator generator, 
            Table document) {
        
        super(speedment, generator, document, Class::of);
    }

    @Override
    protected String getClassOrInterfaceName() {
        return getNamer().javaTypeName(getDocument().getJavaName()) + "ViewImpl";
    }

    @Override
    protected Class makeCodeGenModel(File file) {
        return newBuilder(file, getClassOrInterfaceName())
            .forEveryTable((clazz, table) -> {
                clazz.public_()
                    .setSupertype(Type.of(
                        getSupport().basePackageName() + 
                        ".generated.Generated" + 
                        getNamer().javaTypeName(getDocument().getJavaName()) + 
                        "ViewImpl"
                    ))
                    .add(Type.of(getSupport().entityName() + "View"));
            }).build();
    }

    @Override
    protected String getJavadocRepresentText() {
        return "A materialized object view";
    }
}