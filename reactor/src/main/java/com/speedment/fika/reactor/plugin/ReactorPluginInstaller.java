package com.speedment.fika.reactor.plugin;

import com.speedment.Speedment;
import com.speedment.component.ComponentConstructor;

/**
 * The installer for the Reactor Plugin. Add the following code to the
 * {@code speedment-maven-plugin} in the {@code pom.xml}-file to use this
 * plugin:
 * <p>
 * {@code
 * <configuration>
 *     <components>
 *         <component implementation="
 *             com.speedment.fika.reactor.plugin.ReactorPluginInstaller
 *         " />
 *     </components>
 * </configuration>
 * }
 * 
 * @author Emil Forslund
 * @since  1.1.0
 */
public final class ReactorPluginInstaller 
    implements ComponentConstructor<ReactorPlugin> {

    @Override
    public ReactorPlugin create(Speedment speedment) {
        return new ReactorPlugin(speedment);
    }
}