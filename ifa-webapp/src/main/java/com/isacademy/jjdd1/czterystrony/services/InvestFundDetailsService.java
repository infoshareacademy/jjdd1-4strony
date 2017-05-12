package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.dbviews.Views;
import com.isacademy.jjdd1.czterystrony.model.InvestFundDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("investfunds")
public class InvestFundDetailsService {

    private static Logger log = LoggerFactory.getLogger(InvestFundService.class);

    @Inject
    Views views;

    @GET
    @Path("/details")
    @Produces(MediaType.APPLICATION_JSON)
    public List<InvestFundDetails> getAll() {
        log.info("Provided details for all invest funds.");
        return views.getAllFunds();
    }

    @GET
    @Path("/details/promoted")
    @Produces(MediaType.APPLICATION_JSON)
    public List<InvestFundDetails> getPromoted() {
        log.info("Provided details for promoted invest funds.");
        return views.getPromotedFunds();
    }

    @GET
    @Path("/details/notpromoted")
    @Produces(MediaType.APPLICATION_JSON)
    public List<InvestFundDetails> getNotPromoted() {
        log.info("Provided details for not promoted invest funds.");
        return views.getNotPromotedFunds();
    }
}
