package com.isacademy.jjdd1.czterystrony.services;

import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/investfunds")
public class RatingsService {

    private static final Logger log = LoggerFactory.getLogger(RatingsService.class);

    @Inject
    InvestFundRatingRepository ratingRepository;

    @GET
    @Path("/{id}/ratings")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@PathParam("id") String id) {
        log.info("Provided all ratings for {}", id);
        return Response.ok(ratingRepository.getAllByFund(id)).build();
    }
}
