package com.isacademy.jjdd1.czterystrony.services;

import isacademy.jjdd1.czterystrony.webapp.persistence.dbviews.Views;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("investfunds")
public class InvestFundDetailsService {

    private static final Logger log = LoggerFactory.getLogger(InvestFundDetailsService.class);

    @Inject
    Views views;

    @GET
    @Path("/details")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        log.info("Provided details for all invest funds.");
        return Response.ok(views.getAllFunds()).build();
    }

    @GET
    @Path("/details/promoted")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPromoted() {
        log.info("Provided details for promoted invest funds.");
        return Response.ok(views.getPromotedFunds()).build();
    }

    @GET
    @Path("/details/notpromoted")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotPromoted() {
        log.info("Provided details for not promoted invest funds.");
        return Response.ok(views.getNotPromotedFunds()).build();
    }
}
