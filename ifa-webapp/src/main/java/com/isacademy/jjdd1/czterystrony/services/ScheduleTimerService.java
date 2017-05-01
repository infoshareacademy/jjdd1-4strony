package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.util.FileDownloader;
import com.isacademy.jjdd1.czterystrony.util.UnzipUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.io.IOException;

import static com.isacademy.jjdd1.czterystrony.util.Constants.*;

@Startup
@Singleton
public class ScheduleTimerService {

    private static Logger log = LoggerFactory.getLogger(ScheduleTimerService.class);

    @EJB
    FileDownloader downloader;

    @EJB
    UnzipUtility unzipper;

    @EJB
    DatabaseUpdater updater;

    @PostConstruct
    @Schedule(dayOfWeek = "Mon-Fri", hour = "10", persistent = false)
    void downloadDataAndUpdateInvestFunds() {
        downloadRatings();
        unzipRatings();
        updateInvestFunds();
        insertAllRatings();
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

    @Schedule(minute = "*/3", hour = "*", persistent = false)
    private void updateRatings() {
        try {
            updater.updateRatings();
            log.info("All ratings updated.");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot update ratings.");
        }
    }

    void insertAllRatings() {
        updater.insertAllRatings();
        log.info("All ratings from csv files added to database.");
    }
}