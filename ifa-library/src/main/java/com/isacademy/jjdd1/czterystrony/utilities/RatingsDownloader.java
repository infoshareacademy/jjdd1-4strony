package com.isacademy.jjdd1.czterystrony.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;

import static com.isacademy.jjdd1.czterystrony.Constants.*;

@Stateless
public class RatingsDownloader {

    private static Logger log = LoggerFactory.getLogger(RatingsDownloader.class);

    @Inject
    FileDownloader downloader;

    public void download() {
        try {
            downloader.download(INVEST_FUNDS_RATINGS_ZIP_SOURCE, INVEST_FUNDS_RATINGS_ZIP_PATH);
            log.info("Funds ratings zip downloaded from: {} to {}", INVEST_FUNDS_RATINGS_ZIP_SOURCE, INVEST_FUNDS_RATINGS_ZIP_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot download ratings source from: {}", INVEST_FUNDS_RATINGS_ZIP_SOURCE);
        }
    }
}