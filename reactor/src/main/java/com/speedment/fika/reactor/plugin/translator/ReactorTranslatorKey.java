package com.speedment.fika.reactor.plugin.translator;

import com.speedment.code.TranslatorKey;
import com.speedment.codegen.model.Class;
import com.speedment.codegen.model.Interface;
import com.speedment.config.db.Table;
import com.speedment.internal.core.code.TranslatorKeyImpl;

/**
 *
 * @author Emil Forslund
 * @since  1.1.0
 */
public class ReactorTranslatorKey {
    public final static TranslatorKey<Table, Interface>
        ENTITY_VIEW = new TranslatorKeyImpl<>("entity-view", Interface.class),
        GENERATED_ENTITY_VIEW = new TranslatorKeyImpl<>("generated-entity-view", Interface.class);
    
    public final static TranslatorKey<Table, Class>
        ENTITY_VIEW_IMPL = new TranslatorKeyImpl<>("entity-view-impl", Class.class),
        GENERATED_ENTITY_VIEW_IMPL = new TranslatorKeyImpl<>("enerated-entity-view-impl", Class.class);
}
