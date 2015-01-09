package org.linkcutter.web;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author Lipatov Nikita
 */
public class GuiceConfig extends GuiceServletContextListener {

    public GuiceConfig() {
        super();
        PropertyConfigurator.configure(GuiceConfig.class.getResourceAsStream("/log.properties"));
    }

    @Override
    protected Injector getInjector() {

        return Guice.createInjector(
                new ServletModule() {
                    @Override
                    protected void configureServlets() {

                        install(new JpaPersistModule("db-manager"));

                        ResourceConfig rc = new PackagesResourceConfig("org.linkcutter.web.services");
                        for (Class<?> resource : rc.getClasses()) {
                            bind(resource);
                        }

                        serve("/api/*").with(GuiceContainer.class);

                    }
                },
                new LinkCutterPropertiesModule()
        );
    }
}
