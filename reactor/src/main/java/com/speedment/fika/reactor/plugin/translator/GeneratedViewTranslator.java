package com.speedment.fika.reactor.plugin.translator;

import com.speedment.Speedment;
import com.speedment.codegen.Generator;
import com.speedment.codegen.model.File;
import com.speedment.codegen.model.Generic;
import com.speedment.codegen.model.Interface;
import com.speedment.codegen.model.Type;
import com.speedment.config.db.Table;
import com.speedment.fika.reactor.MaterializedView;
import com.speedment.internal.core.code.DefaultJavaClassTranslator;
import static com.speedment.fika.reactor.plugin.translator.TranslatorUtil.mergingColumnType;

/**
 *
 * @author Emil Forslund
 * @since  1.1.0
 */
public final class GeneratedViewTranslator extends DefaultJavaClassTranslator<Table, Interface> {

    public GeneratedViewTranslator(
            Speedment speedment, 
            Generator generator, 
            Table document) {
        
        super(speedment, generator, document, Interface::of);
    }

    @Override
    protected String getClassOrInterfaceName() {
        return "Generated" + getNamer().javaTypeName(getDocument().getJavaName()) + "View";
    }

    @Override
    public boolean isInGeneratedPackage() {
        return true;
    }

    @Override
    protected Interface makeCodeGenModel(File file) {
        return newBuilder(file, getClassOrInterfaceName())
            .forEveryTable((clazz, table) -> {
                clazz.public_()
                    .add(Type.of(MaterializedView.class)
                        .add(Generic.of().add(getSupport().entityType()))
                        .add(Generic.of().add(mergingColumnType(table)))
                    );
            }).build();
    }

    @Override
    protected String getJavadocRepresentText() {
        return "A materialized object view";
    }
}