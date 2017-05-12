package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.model.Rating;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;
import com.isacademy.jjdd1.czterystrony.repositories.RatingRepository;
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
    InvestFundRepository investFundRepository;

    @Inject
    RatingRepository ratingRepository;

    @GET
    @Path("/{id}/ratings")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rating> getAll(@PathParam("id") String id) {
        log.info("Provided all ratings for {}", id);
        return ratingRepository.getAllByFund(getFund(id));
    }

    private InvestFund getFund(String id) {
        return investFundRepository.getById(id);
    }
}
