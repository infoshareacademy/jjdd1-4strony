package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.analysis.TimeRange;
import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.model.Rating;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;
import com.isacademy.jjdd1.czterystrony.repositories.RatingRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/ratings")
public class Ratings2JsonService {

    @Inject
    InvestFundRepository investFundRepository;

    @Inject
    RatingRepository ratingRepository;

    @GET
    @Path("/json/{investFundId}")
    @Produces("application/json")
    public List<Rating> getRatings(
            @PathParam("investFundId") String id,
            @QueryParam("from") RestDateParam from,
            @QueryParam("to") RestDateParam to) {

        InvestFund investFund = investFundRepository.getById(id);
        TimeRange timeRange = new TimeRange(from.getDate(), to.getDate());
        return ratingRepository.getByFundInTimeRange(investFund, timeRange);
    }
}
