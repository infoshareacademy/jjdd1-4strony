package com.isacademy.jjdd1.czterystrony.clients;

import isacademy.jjdd1.czterystrony.reports.persistence.model.statistics.InstrumentStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

@Stateless
public class ReportsClient {

    private static final Logger log = LoggerFactory.getLogger(ReportsClient.class);
    private static final String INVEST_FUND_STATISTICS_API_URL = "http://4strony-reports-api:8080/api/reports/v1/popularity/investfunds";

    @Inject
    StatisticsCache cache;

    public void post(InstrumentStatistics statistics) {
        WebTarget target = getWebTarget();

        try {
            Response response = target.request().post(Entity.json(statistics));
            response.close();
        } catch (Throwable e) {
            if (!cache.get().contains(statistics)) {
                cache.insert(statistics);
            }
        }
    }

    private WebTarget getWebTarget() {
        Client client = ClientBuilder.newClient();
        return client.target(INVEST_FUND_STATISTICS_API_URL);
    }

    @Schedule(hour = "*", minute = "*/2", persistent = false)
    void postCachedData() {
        if (!cache.isEmpty()) {
            cache.get().forEach(s -> post(s));
            log.info("StatisticsCache emptied and sent to reports module.");
            return;
        }
        log.info("StatisticsCache is empty.");
    }
}
