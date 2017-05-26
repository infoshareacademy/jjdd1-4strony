package com.isacademy.jjdd1.czterystrony.services;

import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFund;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFundRating;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRatingRepository;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/investfunds")
public class RatingsService {

    private static Logger log = LoggerFactory.getLogger(RatingsService.class);

    @Inject
    InvestFundRepository instrumentRepository;

    @Inject
    InvestFundRatingRepository ratingRepository;

    @GET
    @Path("/{id}/ratings")
    @Produces(MediaType.APPLICATION_JSON)
    public List<InvestFundRating> getAll(@PathParam("id") String id) {
        log.info("Provided all ratings for {}", id);
        return ratingRepository.getAllByFund(getFund(id));
    }

    private InvestFund getFund(String id) {
        return instrumentRepository.getById(id);
    }
}
