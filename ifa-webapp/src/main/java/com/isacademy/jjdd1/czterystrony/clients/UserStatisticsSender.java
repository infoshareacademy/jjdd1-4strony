package com.isacademy.jjdd1.czterystrony.clients;

import isacademy.jjdd1.czterystrony.reports.persistence.model.statistics.UserStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Stateless
public class UserStatisticsSender {
    private static final Logger log = LoggerFactory.getLogger(UserStatisticsSender.class);

    @Inject
    UserStatisticsClient client;

    @Asynchronous
    public void send(String name, String email) {
        UserStatistics statistics = new UserStatistics.Builder()
                .withName(name)
                .withEmail(email)
                .withLoginDate(LocalDate.now())
                .withLoginTime(LocalTime.now())
                .build();

        client.post(statistics);
        log.info("User {} logged in. Statistics sent to report module", email);
    }
}
