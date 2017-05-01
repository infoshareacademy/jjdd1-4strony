package com.isacademy.jjdd1.czterystrony.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class ScheduleTimerService {

    private static Logger log = LoggerFactory.getLogger(ScheduleTimerService.class);

    @EJB
    RatingsDownloader ratingsDownloader;

    @EJB
    RatingsUnzipper ratingsUnzipper;

    @EJB
    DatabaseUpdater databaseUpdater;

    @Schedule(dayOfWeek = "Mon-Fri", hour = "10", persistent = false)
    void updateDatabase() {
        ratingsDownloader.download();
        ratingsUnzipper.unzip();
        databaseUpdater.update();
        log.info("Database updated.");
    }
}