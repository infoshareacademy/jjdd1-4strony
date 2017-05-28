package com.isacademy.jjdd1.czterystrony.reports.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("test")
public class TestService {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response testConnection() {
        return Response.ok().build();
    }
}
