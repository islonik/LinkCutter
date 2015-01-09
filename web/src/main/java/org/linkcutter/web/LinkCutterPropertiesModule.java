package org.linkcutter.web;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Lipatov Nikita
 */
public class LinkCutterPropertiesModule extends AbstractModule {

    private final static Logger log = LoggerFactory.getLogger(LinkCutterPropertiesModule.class);

    @Override
    protected void configure() {
        Properties props = new Properties();

        try {
            props.load(LinkCutterPropertiesModule.class.getResourceAsStream("/linkcutter.properties"));

            Names.bindProperties(binder(), props);
        } catch (IOException e) {
            log.warn("Unable to load properties. Properties injection will not be able", e);
        }
    }

}
