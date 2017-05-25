package com.isacademy.jjdd1.czterystrony.services;

import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFund;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRepository;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRatingRepository;
import com.isacademy.jjdd1.czterystrony.analysis.TimeRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

@Path("/investfunds")
public class InvestFundTimeRangeService {

    private static Logger log = LoggerFactory.getLogger(InvestFundTimeRangeService.class);

    @Inject
    InvestFundRepository investFundRepository;

    @Inject
    InvestFundRatingRepository ratingRepository;

    @GET
    @Path("/{id}/timerange")
    @Produces(MediaType.APPLICATION_JSON)
    public TimeRange getZigZagInTimeRange(@PathParam("id") String id) {
        InvestFund investFund = investFundRepository.getById(id);
        LocalDate startDate = ratingRepository.getOldestForFund(investFund).getDate();
        LocalDate endDate = ratingRepository.getNewestForFund(investFund).getDate();
        log.info("Provided time range for {} fund", id);
        return new TimeRange(startDate, endDate);
    }
}
