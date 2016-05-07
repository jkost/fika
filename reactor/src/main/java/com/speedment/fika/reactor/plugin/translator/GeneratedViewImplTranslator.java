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
public final class GeneratedViewImplTranslator extends DefaultJavaClassTranslator<Table, Class> {

    public GeneratedViewImplTranslator(
            Speedment speedment, 
            Generator generator, 
            Table document) {
        
        super(speedment, generator, document, Class::of);
    }

    @Override
    protected String getClassOrInterfaceName() {
        return "Generated" + getNamer().javaTypeName(getDocument().getJavaName()) + "ViewImpl";
    }

    @Override
    public boolean isInGeneratedPackage() {
        return true;
    }

    @Override
    protected Class makeCodeGenModel(File file) {
        return newBuilder(file, getClassOrInterfaceName())
            .forEveryTable((clazz, table) -> {
                clazz.public_().abstract_()
                    .setSupertype(Type.of(MaterializedViewImpl.class)
                        .add(Generic.of().add(getSupport().entityType()))
                        .add(Generic.of().add(mergingColumnType(table)))
                    )
                    .add(Type.of("Generated" + getSupport().entityName() + "View"))
                    .add(Constructor.of().public_().add(
                        "super(" + TranslatorUtil.mergingColumnField(table, getSupport()) + ");"
                    ));
            }).build();
    }

    @Override
    protected String getJavadocRepresentText() {
        return "A materialized object view";
    }
}