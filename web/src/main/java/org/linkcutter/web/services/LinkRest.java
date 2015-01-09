package org.linkcutter.web.services;

import org.linkcutter.impl.services.LinkService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Lipatov Nikita
 */
@Path("links")
@Singleton
public class LinkRest {
    private LinkService linkService;

    @Inject
    public LinkRest(LinkService linkService) {
        this.linkService = linkService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String checkAvailability() {
        return "LinkRest service are running right now!";
    }

    @GET
    @Path("{shorturl}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFullUrl(@PathParam("shorturl") String shorturl) {
        return "longurl/" + shorturl;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String createShortLink(@FormParam("url") String url) {
        return url + "/shourtUrl";
    }


}
