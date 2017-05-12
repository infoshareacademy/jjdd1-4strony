package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;
import com.isacademy.jjdd1.czterystrony.repositories.RatingRepository;
import com.isacademy.jjdd1.czterystrony.technicalanalysis.TimeRange;
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
    RatingRepository ratingRepository;

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
