package com.speedment.fika.reactor.plugin;

import com.speedment.config.db.Column;
import com.speedment.config.db.PrimaryKeyColumn;
import com.speedment.config.db.Table;
import com.speedment.internal.util.document.DocumentDbUtil;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author Emil Forslund
 * @since  1.1.0
 */
public final class ReactorPluginUtil {

    public static List<Column> validMergingColumns(Table table) {
        return table.columns()

            // Only consider non-primary-key columns
            .filter(col -> table.primaryKeyColumns()
                .map(PrimaryKeyColumn::findColumn)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .noneMatch(c -> DocumentDbUtil.isSame(col, c))
            )

            // Nullable columns does not make good
            // merging candidates.
            .filter(col -> !col.isNullable())

            // Only include columns that are 
            // comparable.
            .filter(col -> Comparable.class
                .isAssignableFrom(
                    col.findTypeMapper().getJavaType()
                )
            )

            // Return list of names.
            .collect(toList());
    }
    
    private ReactorPluginUtil() {}
}
