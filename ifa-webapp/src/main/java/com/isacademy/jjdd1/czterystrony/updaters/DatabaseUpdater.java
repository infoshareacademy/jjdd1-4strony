package com.isacademy.jjdd1.czterystrony.updaters;

import com.isacademy.jjdd1.czterystrony.utilities.RatingsDownloader;
import com.isacademy.jjdd1.czterystrony.utilities.RatingsUnzipper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;

@Stateless
public class DatabaseUpdater {
    private static Logger log = LoggerFactory.getLogger(DatabaseUpdater.class);

    @Inject
    RatingsDownloader ratingsDownloader;

    @Inject
    RatingsUnzipper ratingsUnzipper;

    @Inject
    InvestFundsUpdater investFundsUpdater;

    @Inject
    RatingsUpdater ratingsUpdater;

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
