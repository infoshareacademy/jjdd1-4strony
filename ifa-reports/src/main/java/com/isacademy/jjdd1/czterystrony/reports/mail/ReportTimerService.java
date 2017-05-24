package com.isacademy.jjdd1.czterystrony.reports.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class ReportTimerService {

    private static Logger log = LoggerFactory.getLogger(ReportTimerService.class);

    @Inject
    ReportSender reportSender;

    @Schedule(dayOfWeek = "Mon-Fri", hour = "12", minute = "00", persistent = false)
    void sendReport() {
        reportSender.send();
        log.info("Report sent.");
    }
}
