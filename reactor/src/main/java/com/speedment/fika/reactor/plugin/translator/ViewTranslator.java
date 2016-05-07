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
 * Translator that generates the interface for the table specific materialized
 * views.
 * 
 * @author Emil Forslund
 * @since  1.0.0
 */
public final class ViewTranslator extends DefaultJavaClassTranslator<Table, Interface> {

    public ViewTranslator(
            Speedment speedment, 
            Generator generator, 
            Table document) {
        
        super(speedment, generator, document, Interface::of);
    }

    @Override
    protected String getClassOrInterfaceName() {
        return getNamer().javaTypeName(getDocument().getJavaName()) + "View";
    }

    @Override
    protected Interface makeCodeGenModel(File file) {
        return newBuilder(file, getClassOrInterfaceName())
            .forEveryTable((intrf, table) -> {
                intrf.public_().add(Type.of(
                    getSupport().basePackageName() + 
                    ".generated.Generated" + 
                    getNamer().javaTypeName(getDocument().getJavaName()) + 
                    "View"
                ));
            }).build();
    }

    @Override
    protected String getJavadocRepresentText() {
        return "A materialized object view";
    }
}