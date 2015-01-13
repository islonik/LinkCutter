package org.linkcutter.impl.services;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.MethodSorters;
import org.linkcutter.impl.model.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.MalformedURLException;

/**
 * @author Lipatov Nikita
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LinksServiceTest {

    @Autowired
    private LinksService linksService;

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
