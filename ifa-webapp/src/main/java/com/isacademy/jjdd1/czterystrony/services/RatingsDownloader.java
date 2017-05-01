package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.util.FileDownloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;

import static com.isacademy.jjdd1.czterystrony.util.Constants.*;

@Stateless
public class RatingsDownloader {

    private static Logger log = LoggerFactory.getLogger(ScheduleTimerService.class);

    @EJB
    private FileDownloader downloader;

    void download() {
        try {
            downloader.download(FUNDS_RATINGS_ZIP_SOURCE, FUNDS_RATINGS_ZIP_PATH);
            log.info("Funds ratings zip downloaded from: {} to {}", FUNDS_RATINGS_ZIP_SOURCE, FUNDS_RATINGS_ZIP_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot download ratings source from: {}", FUNDS_RATINGS_ZIP_SOURCE);
        }
    }
}
