package org.linkcutter.impl.dao;

import atunit.AtUnit;
import atunit.Container;
import atunit.Unit;
import com.google.inject.*;
import com.google.inject.name.Names;
import com.google.inject.persist.jpa.JpaPersistModule;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.linkcutter.impl.ApplicationInitializer;
import org.linkcutter.impl.dao.impl.LinksDaoImpl;
import org.linkcutter.impl.model.Link;
import org.linkcutter.impl.services.LinksService;

import java.util.List;

/**
 * @author Lipatov Nikita
 */
@RunWith(AtUnit.class)
@Container(Container.Option.GUICE)
public class LinksDaoTest implements Module {

    @Inject
    @Unit
    LinksService linksService;

    public void configure(Binder b) {
        b.bind(String.class).annotatedWith(Names.named("linkcutter.host")).toInstance("http://localhost/");
    }

    @Test
    public void generalLinkDaoTest() {
        Injector injector = Guice.createInjector(new JpaPersistModule("db-manager"));
        injector.getInstance(ApplicationInitializer.class);
        LinksDao linksDao = injector.getInstance(LinksDaoImpl.class);

        // Persist
        Link link1 = new Link();
        link1.setUrl("http://host:8080/somereallylonglink");
        linksService.cut(link1);
        linksDao.create(link1);

        Link link2 = new Link();
        link2.setUrl("http://longhost:/somestuff");
        linksService.cut(link2);
        linksDao.create(link2);

        Link link3 = new Link();
        link3.setUrl("http://longhost:8080/somereallylonglink");
        linksService.cut(link3);
        linksDao.create(link3);

        Assert.assertNotNull(linksDao.find(link1.getId()));
        Assert.assertNotNull(linksDao.find(link2.getId()));
        Assert.assertNotNull(linksDao.find(link3.getId()));

        List<Link> links = linksDao.findAll();
        Assert.assertEquals(3, links.size());
        for(Link link : links) {
            System.out.println("url = " + link.getUrl());
            System.out.println("short url = " + link.getShortUrl());
        }
    }
}
