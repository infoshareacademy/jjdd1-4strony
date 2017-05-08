package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.analysis.TimeRange;
import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.model.Rating;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;
import com.isacademy.jjdd1.czterystrony.repositories.RatingRepository;
import com.isacademy.jjdd1.czterystrony.technicalanalysis.AnalysisAudit;
import com.isacademy.jjdd1.czterystrony.technicalanalysis.LocalExtremaProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import java.util.List;

@Path("/zigzag")
public class LocalExtrema2JsonService {

    private static Logger log = LoggerFactory.getLogger(Ratings2JsonService.class);

    @Inject
    InvestFundRepository investFundRepository;

    @Inject
    RatingRepository ratingRepository;

    @Inject
    LocalExtremaProvider localExtremaProvider;

    @GET
    @Path("/timeRange/json/{investFundId}")
    @Produces("application/json")
    public List<Rating> getRatingsInTimeRange(
            @PathParam("investFundId") String id,
            @QueryParam("from") RestDateParam from,
            @QueryParam("to") RestDateParam to,
            @QueryParam("zigZag") RestIntegerParam zigZag) {

        TimeRange timeRange = new TimeRange(from.getDate(), to.getDate());
        List<Rating> ratings = ratingRepository.getByFundInTimeRange(getFund(id), timeRange);
        log.info("Provided local extrema for {} from {} to {}", id, from.getDate(), to.getDate());
        return localExtremaProvider.findExtrema(ratings, zigZag.getNumber());
    }

    @GET
    @Path("/all/json/{investFundId}")
    @Produces("application/json")
    @Interceptors(AnalysisAudit.class)
    public List<Rating> getRatings(
            @PathParam("investFundId") String id,
            @QueryParam("zigZag") RestIntegerParam zigZag) {

        List<Rating> ratings = ratingRepository.getAllByFund(getFund(id));
        log.info("Provided all local extrema for {}", id);
        return localExtremaProvider.findExtrema(ratings, zigZag.getNumber());
    }

    private InvestFund getFund(String id) {
        return investFundRepository.getById(id);
    }
}
