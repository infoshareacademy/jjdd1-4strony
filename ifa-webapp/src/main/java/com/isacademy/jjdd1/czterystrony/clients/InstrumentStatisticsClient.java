package com.isacademy.jjdd1.czterystrony.clients;

import isacademy.jjdd1.czterystrony.reports.persistence.model.statistics.InstrumentStatistics;
import isacademy.jjdd1.czterystrony.reports.persistence.model.statistics.InvestFundStatistics;
import isacademy.jjdd1.czterystrony.reports.persistence.model.statistics.PensionFundStatistics;
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
public class InstrumentStatisticsClient {

    private static final Logger log = LoggerFactory.getLogger(InstrumentStatisticsClient.class);
    private static final String INVEST_FUND_STATISTICS_API_URL = "http://4strony-reports-api:8080/api/statistics/v1/investfunds";
    private static final String PENSION_FUND_STATISTICS_API_URL = "http://4strony-reports-api:8080/api/statistics/v1/pensionfunds";
    private static final String API_TEST_URL = "http://4strony-reports-api:8080/api/test";

    @Inject
    InstrumentStatisticsCache cache;

    public void post(InstrumentStatistics statistics) {
        WebTarget target = getWebTarget(statistics.getClass());

        try {
            Response response = target.request().post(Entity.json(statistics));
            response.close();
        } catch (Throwable e) {
            if (!cache.get().contains(statistics)) {
                cache.insert(statistics);
            }
        }
    }

    private WebTarget getWebTarget(Class type) {
        Client client = ClientBuilder.newClient();

        if (type.equals(InvestFundStatistics.class)) {
            return client.target(INVEST_FUND_STATISTICS_API_URL);
        }

        if (type.equals(PensionFundStatistics.class)) {
            return client.target(PENSION_FUND_STATISTICS_API_URL);
        }

        return null;
    }

    @Schedule(hour = "*", minute = "*/2", persistent = false)
    void postCachedData() {
        if (!cache.isEmpty()) {
            if (apiIsAvailable()) {
                cache.get().forEach(s -> post(s));
                cache.clear();
                log.info("InstrumentStatisticsCache emptied and sent to reports module.");
                return;
            }
            log.info("REPORTS API is still unavailable.");
            return;
        }
        log.info("InstrumentStatisticsCache is empty.");
    }

    private boolean apiIsAvailable() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(API_TEST_URL);

        try {
            Response response = target.request().get();
            response.close();
            return response.getStatus() == 200;
        } catch (Throwable e) {
            return false;
        }
    }
}
