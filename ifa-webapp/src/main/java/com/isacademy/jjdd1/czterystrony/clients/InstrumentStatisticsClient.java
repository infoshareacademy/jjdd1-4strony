package com.isacademy.jjdd1.czterystrony.clients;

import isacademy.jjdd1.czterystrony.reports.persistence.model.statistics.InstrumentStatistics;
import isacademy.jjdd1.czterystrony.reports.persistence.model.statistics.InvestFundStatistics;
import isacademy.jjdd1.czterystrony.reports.persistence.model.statistics.PensionFundStatistics;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Stateless
public class InstrumentStatisticsClient extends StatisticsClient<InstrumentStatistics> {

    private static final String INVEST_FUND_STATISTICS_API_URL = "http://4strony-reports-api:8080/api/statistics/v1/investfunds";
    private static final String PENSION_FUND_STATISTICS_API_URL = "http://4strony-reports-api:8080/api/statistics/v1/pensionfunds";

    @Inject
    InstrumentStatisticsCache cache;

    public void post(InstrumentStatistics statistics) {
        WebTarget target = getWebTarget(statistics.getClass());
        super.post(target, cache, statistics);
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
        super.postCachedData(cache);
    }
}
