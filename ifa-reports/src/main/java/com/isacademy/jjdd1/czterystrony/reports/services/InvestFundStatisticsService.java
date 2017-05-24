package com.isacademy.jjdd1.czterystrony.reports.services;

import com.isacademy.jjdd1.czterystrony.reports.model.statistics.InstrumentStatistics;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("statistics/v1/investfunds")
public class InvestFundStatisticsService implements StatisticsService {

    @Override
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStatistics(InstrumentStatistics statistics, @Context UriInfo uriInfo) {
        //TODO
        try {
            URI resourceLocation = getResourceLocation(uriInfo);
            return Response.created(resourceLocation).build();
        } catch (Throwable e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    //TODO
    private URI getResourceLocation(UriInfo uriInfo) {
        return uriInfo.getAbsolutePathBuilder()
                .path("...")
                .build();
    }
}
