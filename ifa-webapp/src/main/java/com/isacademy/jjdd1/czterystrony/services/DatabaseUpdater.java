package com.isacademy.jjdd1.czterystrony.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;

@Stateless
public class DatabaseUpdater {

    private static Logger log = LoggerFactory.getLogger(ScheduleTimerService.class);

    @EJB
    private InvestFundUpdater investFundUpdater;

    @EJB
    private RatingUpdater ratingUpdater;

    @PostConstruct
    private void updateInvestFunds() {
        try {
            investFundUpdater.update();
            log.info("Invest funds updated.");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot update invest funds.");
        }
    }

    void update() {
        try {
            ratingUpdater.update();
            log.info("All ratings updated.");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot update ratings.");
        }
    }
}
