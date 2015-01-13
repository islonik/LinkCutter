package org.linkcutter.impl.services;

import atunit.AtUnit;
import atunit.Container;
import atunit.Unit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.name.Names;
import org.linkcutter.impl.model.Link;

import java.net.MalformedURLException;

/**
 * @author Lipatov Nikita
 */
@RunWith(AtUnit.class)
@Container(Container.Option.GUICE)
public class LinksServiceTest implements Module {

    @Inject
    @Unit
    LinksService linksService;

    /*
     * If your test does implement Module, the module configuration will be
     * merged with any bindings created by AtUnit.
     *
     * This example is fairly contrived. Typically you'll just want to inject
     * mock objects into your @Unit object, and AtUnit can do that without
     * making you explicitly configure the bindings yourself. See
     * ExampleGuiceAndJMockTest for an example.
     *
     */
    public void configure(Binder b) {
        b.bind(String.class).annotatedWith(Names.named("linkcutter.host")).toInstance("http://localhost/");
    }

    @Test
    public void simpleTest() throws MalformedURLException {
        Link link = new Link();
        link.setUrl("http://host:8080/somereallylonglink");

        Assert.assertNull(link.getShortUrl());
        Assert.assertNull(link.getCreationDate());

        linksService.cut(link);

        Assert.assertNotNull(link.getShortUrl());
        Assert.assertNotNull(link.getCreationDate());
    }
}
