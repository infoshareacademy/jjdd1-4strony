package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.analysis.ExponentialMovingAverage;
import com.isacademy.jjdd1.czterystrony.analysis.SimpleMovingAverage;
import com.isacademy.jjdd1.czterystrony.analysis.WeightedMovingAverage;
import com.isacademy.jjdd1.czterystrony.restparameters.DateParam;
import com.isacademy.jjdd1.czterystrony.restparameters.IntegerParam;
import com.isacademy.jjdd1.czterystrony.technicalanalysis.MovingAverageProvider;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFundRating;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Path("/investfunds")
public class AveragesService {

    private static Logger log = LoggerFactory.getLogger(AveragesService.class);

    @Inject
    InvestFundRatingRepository ratingRepository;

    @Inject
    MovingAverageProvider averageProvider;

    @GET
    @Path("/{id}/average")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getZigZag(
            @PathParam("id") String id,
            @QueryParam("period") IntegerParam period,
            @QueryParam("startDate") DateParam start,
            @QueryParam("endDate") DateParam end) {

        int periodValue = period.getNumber();
        periodValue = periodValue <= 0 ? 1 : periodValue;
        List<InvestFundRating> simpleAverage = averageProvider.get(getRatings(id), new SimpleMovingAverage(periodValue));
        List<InvestFundRating> weightedAverage = averageProvider.get(getRatings(id), new WeightedMovingAverage(periodValue));
        List<InvestFundRating> exponentialAverage = averageProvider.get(getRatings(id), new ExponentialMovingAverage(periodValue));

        List<List<InvestFundRating>> averages = Arrays.asList(simpleAverage, weightedAverage, exponentialAverage);
        log.info("Provided moving averages for {}", id);
        return Response.ok(averages).build();
    }

    private List<InvestFundRating> getRatings(String id) {
        return ratingRepository.getAllByFund(id);
    }
}
