package com.isacademy.jjdd1.czterystrony.reports.services;

import isacademy.jjdd1.czterystrony.reports.persistence.model.statistics.UserStatistics;
import isacademy.jjdd1.czterystrony.reports.persistence.repositories.UserStatisticsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("statistics/v1/users")
public class UserStatisticsService {
    private static final Logger log = LoggerFactory.getLogger(UserStatisticsService.class);

    @Inject
    UserStatisticsRepository repository;

    @Context
    UriInfo uriInfo;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public Response createStatistics(UserStatistics userStatistics) {
        try {
            Long id = repository.add(userStatistics);

            URI resourceLocation = uriInfo.getAbsolutePathBuilder()
                    .path(id.toString())
                    .build();

            return Response.created(resourceLocation).build();
        } catch (Throwable e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatistics(@PathParam("id") Long id) {
        try {
            UserStatistics statistics = repository.getById(id);
            log.info("Provided statistic with ID = {}", id);
            return Response.ok(statistics).build();
        } catch (Throwable e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
