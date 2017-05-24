package com.isacademy.jjdd1.czterystrony.reports.services;

import com.isacademy.jjdd1.czterystrony.reports.model.statistics.InstrumentStatistics;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public interface StatisticsService {

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    Response createStatistics(InstrumentStatistics statistics, @Context UriInfo uriInfo);
}
