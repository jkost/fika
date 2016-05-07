package com.speedment.fika.reactor.plugin.translator;

import com.speedment.code.TranslatorSupport;
import com.speedment.codegen.model.Type;
import com.speedment.config.db.Column;
import com.speedment.config.db.Table;
import com.speedment.fika.reactor.plugin.ReactorPlugin;
import static com.speedment.fika.reactor.plugin.ReactorPluginUtil.validMergingColumns;

/**
 * Utility methods that are used by several translators in this package but that
 * doesn't nescessarily need to be shared with others.
 * 
 * @author Emil Forslund
 * @since  1.1.0
 */
final class TranslatorUtil {
    
    static Column mergingColumn(Table table) {
        return table.getAsString(ReactorPlugin.MERGE_ON)
            .flatMap(str -> table.columns()
                .filter(col -> col.getName().equals(str))
                .findAny()
            )
            .map(Column.class::cast)
            .orElseGet(() -> 
                validMergingColumns(table).get(0)
            );
    }
    
    static String mergingColumnField(Table table, TranslatorSupport<Table> support) {
        return support.namer().javaTypeName(table.getJavaName()) + "." +
            support.namer().javaStaticFieldName(
                mergingColumn(table).getJavaName()
            );
    }

    static Type mergingColumnType(Table table) {
        return Type.of(mergingColumn(table).findTypeMapper().getJavaType());
    }
    
    private TranslatorUtil() {}
}
