package com.isacademy.jjdd1.czterystrony.services;

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
import javax.ws.rs.core.MediaType;
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
    @Path("/all/json/{investFundId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Interceptors(AnalysisAudit.class)
    public List<Rating> getZigZag(
            @PathParam("investFundId") String id,
            @QueryParam("zigZag") RestIntegerParam zigZag,
            @QueryParam("startDate") RestDateParam start,
            @QueryParam("endDate") RestDateParam end) {

        List<Rating> ratings = ratingRepository.getAllByFund(getFund(id));
        log.info("Provided all local extrema for {}", id);
        return localExtremaProvider.findExtrema(ratings, zigZag.getNumber());
    }

    private InvestFund getFund(String id) {
        return investFundRepository.getById(id);
    }
}
