package org.linkcutter.web.services;

import org.linkcutter.impl.dao.LinksDao;
import org.linkcutter.impl.model.Link;
import org.linkcutter.impl.services.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Lipatov Nikita
 */
@Path("links")
@Component
public class LinkRest {
    @Autowired
    private LinksDao linksDao;
    @Autowired
    private LinksService linksService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String checkAvailability() {
        return "LinkRest service are running right now!";
    }

    @GET
    @Path("{shorturl}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFullUrl(@PathParam("shorturl") String shorturl) throws URISyntaxException {

        Link link = linksDao.findByShortUrl(linksService.getHost() + "/" + shorturl);
        if(link != null) {
            URI redirect = new URI(link.getUrl());
            return Response.temporaryRedirect(redirect).build();
        }
        return Response.noContent().build(); // 204 code
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String createShortLink(@FormParam("url") String url) throws MalformedURLException {
        try {
            URL urlValidated = new URL(url);

            Link link = new Link();
            link.setUrl(urlValidated.toString());

            linksService.cut(link);

            linksDao.create(link);

            return link.getShortUrl();
        } catch (MalformedURLException murle) {
            return "Please send a URL instead of " + url;
        }

    }


}
