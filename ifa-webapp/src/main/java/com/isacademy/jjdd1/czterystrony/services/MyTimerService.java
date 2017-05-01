package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.util.FileDownloader;
import com.isacademy.jjdd1.czterystrony.util.UnzipUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.*;
import java.io.IOException;

import static com.isacademy.jjdd1.czterystrony.util.Constants.*;

@Stateless
public class MyTimerService {

    private static Logger log = LoggerFactory.getLogger(MyTimerService.class);

    @EJB
    FileDownloader downloader;

    @EJB
    UnzipUtility unzipper;

    @EJB
    DatabaseUpdater updater;

    @Schedule(minute = "1/30", hour = "*", persistent = false)
    void updateDatabase1() {
//        downloadRatings();
//        unzipRatings();
//        updateInvestFunds();
    }

    @Schedule(minute = "17/30", hour = "*", persistent = false)
    void updateDatabase2() {
        insertAllRatings();
        //        updateRatings();
    }

    private void downloadRatings() {
        try {
            downloader.download(FUNDS_RATINGS_ZIP_SOURCE, FUNDS_RATINGS_ZIP_PATH);
            log.info("Funds ratings zip downloaded from: {} to {}", FUNDS_RATINGS_ZIP_SOURCE, FUNDS_RATINGS_ZIP_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot download ratings source from: {}", FUNDS_RATINGS_ZIP_SOURCE);
        }
    }

    private void unzipRatings() {
        try {
            unzipper.unzip(FUNDS_RATINGS_ZIP_PATH, TMP_PROJECT_FOLDER);
            log.info("Funds ratings unzipped to: {}", TMP_PROJECT_FOLDER);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot uznip ratings file: {} to: {}", FUNDS_RATINGS_ZIP_PATH, TMP_PROJECT_FOLDER);
        }
    }

    private void updateInvestFunds() {
        try {
            updater.updateInvestFunds();
            log.info("Invest funds updated.");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot update invest funds.");
        }
    }

    private void updateRatings() {
        try {
            updater.updateRatings();
            log.info("All ratings updated.");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot update ratings.");
        }
    }

    private void insertAllRatings() {
        updater.insertAllRatings();
        log.info("All ratings updated.");
    }
}