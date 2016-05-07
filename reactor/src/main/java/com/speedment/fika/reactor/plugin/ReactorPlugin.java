package com.speedment.fika.reactor.plugin;

import com.speedment.Speedment;
import com.speedment.code.StandardTranslatorKey;
import com.speedment.component.CodeGenerationComponent;
import com.speedment.component.Component;
import com.speedment.component.EventComponent;
import com.speedment.component.UserInterfaceComponent;
import com.speedment.config.db.Column;
import com.speedment.config.db.Project;
import com.speedment.config.db.Table;
import com.speedment.event.TreeSelectionChange;
import static com.speedment.fika.reactor.plugin.ReactorPluginUtil.validMergingColumns;
import com.speedment.fika.reactor.plugin.translator.GeneratedApplicationDecorator;
import com.speedment.fika.reactor.plugin.translator.GeneratedViewImplTranslator;
import com.speedment.fika.reactor.plugin.translator.GeneratedViewTranslator;
import com.speedment.fika.reactor.plugin.translator.ReactorTranslatorKey;
import com.speedment.fika.reactor.plugin.translator.ViewImplTranslator;
import com.speedment.fika.reactor.plugin.translator.ViewTranslator;
import com.speedment.internal.core.platform.component.impl.AbstractComponent;
import com.speedment.internal.license.AbstractSoftware;
import static com.speedment.internal.license.OpenSourceLicense.APACHE_2;
import com.speedment.internal.ui.config.DocumentProperty;
import com.speedment.internal.ui.config.TableProperty;
import com.speedment.internal.ui.property.ChoicePropertyItem;
import com.speedment.license.Software;
import static java.util.stream.Collectors.toList;
import javafx.scene.control.TreeItem;
import javafx.util.StringConverter;
import static javafx.collections.FXCollections.observableList;

/**
 *
 * @author Emil Forslund
 * @since  1.1.0
 */
public final class ReactorPlugin extends AbstractComponent {
    
    public final static String MERGE_ON = "mergeOn";

    public ReactorPlugin(Speedment speedment) {
        super(speedment);
    }

    @Override
    public void onResolve() {
        super.onResolve();
        
        final CodeGenerationComponent code = getSpeedment().getCodeGenerationComponent();
        code.add(Project.class, StandardTranslatorKey.GENERATED_APPLICATION, new GeneratedApplicationDecorator());
        
        code.put(Table.class, ReactorTranslatorKey.ENTITY_VIEW, ViewTranslator::new);
        code.put(Table.class, ReactorTranslatorKey.ENTITY_VIEW_IMPL, ViewImplTranslator::new);
        code.put(Table.class, ReactorTranslatorKey.GENERATED_ENTITY_VIEW, GeneratedViewTranslator::new);
        code.put(Table.class, ReactorTranslatorKey.GENERATED_ENTITY_VIEW_IMPL, GeneratedViewImplTranslator::new);
        
        final UserInterfaceComponent ui = getSpeedment().getUserInterfaceComponent();
        final EventComponent events = getSpeedment().getEventComponent();
        
        events.on(TreeSelectionChange.class, ev -> {
            ev.changeEvent()
                .getList()
                .stream()
                .map(TreeItem<DocumentProperty>::getValue)
                .findAny()
                .ifPresent(doc -> {
                    if (doc instanceof TableProperty) {
                        System.out.println("It was a table.");
                        final TableProperty table = (TableProperty) doc;

                        ui.getProperties().add(new ChoicePropertyItem<>(
                            observableList(
                                validMergingColumns(table).stream()
                                    .map(Column::getJavaName)
                                    .collect(toList())
                            ),
                            table.stringPropertyOf(MERGE_ON, () -> null),
                            new StringConverter<String>() {
                                @Override
                                public String toString(String str) {
                                    return str;
                                }

                                @Override
                                public String fromString(String str) {
                                    return str;
                                }
                            }, 
                            String.class,
                            "Merge events on", 
                            "This column will be used to merge events in a " + 
                            "materialized object view (MOV) so that only the " + 
                            "most recent revision of an entity is visible."
                        ));
                    }
                });
        });
    }

    @Override
    public Class<? extends Component> getComponentClass() {
        return ReactorPlugin.class;
    }

    @Override
    public Software asSoftware() {
        return AbstractSoftware.with(
            "Reactor Plugin", "1.1.0", APACHE_2
        );
    }

    @Override
    public Component defaultCopy(Speedment speedment) {
        return new ReactorPlugin(speedment);
    }
}