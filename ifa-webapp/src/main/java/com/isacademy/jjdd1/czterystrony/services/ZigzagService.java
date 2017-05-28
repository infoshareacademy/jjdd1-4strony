package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.restparameters.DateParam;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFundRating;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRatingRepository;
import com.isacademy.jjdd1.czterystrony.restparameters.IntegerParam;
import com.isacademy.jjdd1.czterystrony.interceptors.ZigzagAudit;
import com.isacademy.jjdd1.czterystrony.technicalanalysis.LocalExtremaProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/investfunds")
public class ZigzagService {

    private static Logger log = LoggerFactory.getLogger(ZigzagService.class);

    @Inject
    InvestFundRatingRepository ratingRepository;

    @Inject
    LocalExtremaProvider localExtremaProvider;

    @GET
    @Path("/{id}/zigzag")
    @Produces(MediaType.APPLICATION_JSON)
    @Interceptors(ZigzagAudit.class)
    public Response getZigZag(
            @PathParam("id") String id,
            @QueryParam("value") IntegerParam zigZag,
            @QueryParam("startDate") DateParam start,
            @QueryParam("endDate") DateParam end) {

        List<InvestFundRating> ratings = ratingRepository.getAllByFund(id);
        List<InvestFundRating> localExtrema = localExtremaProvider.findExtrema(ratings, zigZag.getNumber());
        log.info("Provided all local extrema for {}", id);
        return Response.ok(localExtrema).build();
    }
}
