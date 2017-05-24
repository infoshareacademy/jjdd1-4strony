package com.isacademy.jjdd1.czterystrony.services;

import isacademy.jjdd1.czterystrony.webapp.persistance.model.InvestFund;
import isacademy.jjdd1.czterystrony.webapp.persistance.repositories.InvestFundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/investfunds")
public class InvestFundService {

    private static Logger log = LoggerFactory.getLogger(InvestFundService.class);

    @Inject
    InvestFundRepository repository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public InvestFund getById(@PathParam("id") String id) {
        log.info("Provided info for {} fund", id);
        return repository.getById(id);
    }
}
