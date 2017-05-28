package com.isacademy.jjdd1.czterystrony.clients;

import isacademy.jjdd1.czterystrony.reports.persistence.model.statistics.UserStatistics;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Stateless
public class UserStatisticsClient extends StatisticsClient<UserStatistics> {

    private static final String USER_STATISTICS_API_URL = "http://4strony-reports-api:8080/api/statistics/v1/users";

    @Inject
    UserStatisticsCache cache;

    public void post(UserStatistics statistics) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(USER_STATISTICS_API_URL);
        super.post(target, cache, statistics);
    }

    @Schedule(hour = "*", minute = "*/2", persistent = false)
    void postCachedData() {
        super.postCachedData(cache);
    }
}
