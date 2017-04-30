package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.util.FileDownloader;
import com.isacademy.jjdd1.czterystrony.util.UnzipUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.io.IOException;

import static com.isacademy.jjdd1.czterystrony.util.Constants.*;

@Singleton
public class MyTimerService {

    private static Logger log = LoggerFactory.getLogger(MyTimerService.class);

    @EJB
    FileDownloader downloader;

    @EJB
    UnzipUtility unzipper;

    @Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
    void downloadAndUnzipFundsRatings() {
        try {
            downloader.download(FUNDS_RATINGS_ZIP_SOURCE, FUNDS_RATINGS_ZIP_PATH);
            log.debug("Funds ratings zip downloaded from: {} to {}", FUNDS_RATINGS_ZIP_SOURCE, FUNDS_RATINGS_ZIP_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot download ratings source from: {}", FUNDS_RATINGS_ZIP_SOURCE);
        }

        try {
            unzipper.unzip(FUNDS_RATINGS_ZIP_PATH, TMP_PROJECT_FOLDER);
            log.debug("Funds ratings unzipped to: {}", TMP_PROJECT_FOLDER);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot uznip ratings file: {} to: {}", FUNDS_RATINGS_ZIP_PATH, TMP_PROJECT_FOLDER);
        }
    }
}
