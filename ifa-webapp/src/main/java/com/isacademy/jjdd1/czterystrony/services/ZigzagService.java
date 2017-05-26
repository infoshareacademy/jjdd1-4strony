package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.restparameters.DateParam;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFund;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFundRating;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRatingRepository;
import com.isacademy.jjdd1.czterystrony.restparameters.IntegerParam;
import com.isacademy.jjdd1.czterystrony.technicalanalysis.AnalysisAudit;
import com.isacademy.jjdd1.czterystrony.technicalanalysis.LocalExtremaProvider;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/investfunds")
public class ZigzagService {

    private static Logger log = LoggerFactory.getLogger(RatingsService.class);

    @Inject
    InvestFundRepository instrumentRepository;

    @Inject
    InvestFundRatingRepository ratingRepository;

    @Inject
    LocalExtremaProvider localExtremaProvider;

    @GET
    @Path("/{id}/zigzag")
    @Produces(MediaType.APPLICATION_JSON)
    @Interceptors(AnalysisAudit.class)
    public List<InvestFundRating> getZigZag(
            @PathParam("id") String id,
            @QueryParam("value") IntegerParam zigZag,
            @QueryParam("startDate") DateParam start,
            @QueryParam("endDate") DateParam end) {

        List<InvestFundRating> ratings = ratingRepository.getAllByFund(getFund(id));
        log.info("Provided all local extrema for {}", id);
        return localExtremaProvider.findExtrema(ratings, zigZag.getNumber());
    }

    private InvestFund getFund(String id) {
        return instrumentRepository.getById(id);
    }
}
