package com.isacademy.jjdd1.czterystrony.updaters;

import com.isacademy.jjdd1.czterystrony.services.RatingsDownloader;
import com.isacademy.jjdd1.czterystrony.services.RatingsUnzipper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;

@Stateless
public class DatabaseUpdater {
    private static Logger log = LoggerFactory.getLogger(DatabaseUpdater.class);

    @EJB
    private RatingsDownloader ratingsDownloader;

    @EJB
    private RatingsUnzipper ratingsUnzipper;

    @EJB
    private InvestFundsUpdater investFundsUpdater;

    @EJB
    private RatingsUpdater ratingsUpdater;

    public void update() {
        ratingsDownloader.download();
        ratingsUnzipper.unzip();
        updateInvestFunds();
        updateRatings();
    }

    private void updateInvestFunds() {
        try {
            investFundsUpdater.update();
            log.info("Invest funds updated.");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot update invest funds.");
        }
    }

    private void updateRatings() {
        try {
            ratingsUpdater.update();
            log.info("All ratings updated.");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot update ratings.");
        }
    }
}
