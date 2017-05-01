package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.updaters.DatabaseUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class ScheduleTimerService {

    private static Logger log = LoggerFactory.getLogger(ScheduleTimerService.class);

    @EJB
    private DatabaseUpdater databaseUpdater;

    @Schedule(dayOfWeek = "Mon-Fri", hour = "10", minute = "30", persistent = false)
    void updateDatabase() {
        databaseUpdater.update();
        log.info("Database updated.");
    }
}