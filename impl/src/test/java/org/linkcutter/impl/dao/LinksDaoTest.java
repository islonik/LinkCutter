package org.linkcutter.impl.dao;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.linkcutter.impl.model.Link;
import org.linkcutter.impl.services.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Lipatov Nikita
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LinksDaoTest {

    @Autowired
    private LinksDao linksDao;

    @Autowired
    private LinksService linksService;

    @Test
    public void generalLinkDaoTest() {

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
