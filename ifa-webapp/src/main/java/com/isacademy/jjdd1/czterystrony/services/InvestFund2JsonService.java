package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/investfund")
public class InvestFund2JsonService {

    private static Logger log = LoggerFactory.getLogger(InvestFund2JsonService.class);

    @Inject
    InvestFundRepository investFundRepository;

    @GET
    @Path("/json/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public InvestFund getInvestFund(@PathParam("id") String id) {
        log.info("Provided info for {} fund", id);
        return investFundRepository.getById(id);
    }
}
