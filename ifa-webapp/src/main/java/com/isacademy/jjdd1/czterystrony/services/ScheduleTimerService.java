package com.isacademy.jjdd1.czterystrony.services;

import isacademy.jjdd1.czterystrony.webapp.persistence.dbviews.Views;
import isacademy.jjdd1.czterystrony.webapp.persistence.updaters.DatabaseUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class ScheduleTimerService {

    private static final Logger log = LoggerFactory.getLogger(ScheduleTimerService.class);

    @Inject
    DatabaseUpdater databaseUpdater;

    @Inject
    Views views;

    @Schedule(dayOfWeek = "Mon-Fri", hour = "10", minute = "25", persistent = false)
    void updateDatabase() {
        databaseUpdater.update();
        log.info("Database updated.");
    }

    @Schedule(dayOfWeek = "Mon-Fri", hour = "10", minute = "30", persistent = false)
    void updateViews() {
        views.updateViews();
        log.info("Views updated.");
    }
}