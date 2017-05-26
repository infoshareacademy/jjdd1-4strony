package com.isacademy.jjdd1.czterystrony.clients;

import isacademy.jjdd1.czterystrony.reports.persistence.model.statistics.InstrumentStatistics;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Stateless
public class StatisticsClient {

    @Context
    UriInfo uriInfo;

    @Inject
    StatisticsCache cache;

    public void post(InstrumentStatistics statistics) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8735/api/statistics/v1/investfunds");
        Response response = target.request().post(Entity.json(statistics));
        response.close();

        if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            cache.insert(statistics);
        }
    }

    @Schedule(hour = "*", minute = "*/2", persistent = false)
    void postCachedData() {
        if (!cache.isEmpty()) {
            cache.get().forEach(s -> post(s));
        }
    }
}
