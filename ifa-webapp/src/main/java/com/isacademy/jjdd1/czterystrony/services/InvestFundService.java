package com.isacademy.jjdd1.czterystrony.services;

import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/investfunds")
public class InvestFundService {

    private static final Logger log = LoggerFactory.getLogger(InvestFundService.class);

    @Inject
    InvestFundRepository repository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") String id) {
        log.info("Provided info for {} fund", id);
        return Response.ok(repository.getById(id)).build();
    }
}
