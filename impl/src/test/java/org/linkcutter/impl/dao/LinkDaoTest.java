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
import org.linkcutter.impl.dao.impl.LinkDaoImpl;
import org.linkcutter.impl.model.Link;
import org.linkcutter.impl.services.LinkService;

import java.util.List;

/**
 * @author Lipatov Nikita
 */
@RunWith(AtUnit.class)
@Container(Container.Option.GUICE)
public class LinkDaoTest implements Module {

    @Inject
    @Unit
    LinkService linkService;

    public void configure(Binder b) {
        b.bind(String.class).annotatedWith(Names.named("linkcutter.host")).toInstance("http://localhost/");
    }

    @Test
    public void generalLinkDaoTest() {
        Injector injector = Guice.createInjector(new JpaPersistModule("db-manager"));
        injector.getInstance(ApplicationInitializer.class);
        LinkDao linkDao = injector.getInstance(LinkDaoImpl.class);

        // Persist
        Link link1 = new Link();
        link1.setUrl("http://host:8080/somereallylonglink");
        linkService.cut(link1);
        linkDao.create(link1);

        Link link2 = new Link();
        link2.setUrl("http://longhost:/somestuff");
        linkService.cut(link2);
        linkDao.create(link2);

        Link link3 = new Link();
        link3.setUrl("http://longhost:8080/somereallylonglink");
        linkService.cut(link3);
        linkDao.create(link3);

        Assert.assertNotNull(linkDao.find(link1.getId()));
        Assert.assertNotNull(linkDao.find(link2.getId()));
        Assert.assertNotNull(linkDao.find(link3.getId()));

        List<Link> links = linkDao.findAll();
        Assert.assertEquals(3, links.size());
        for(Link link : links) {
            System.out.println("url = " + link.getUrl());
            System.out.println("short url = " + link.getShortUrl());
        }
    }
}
