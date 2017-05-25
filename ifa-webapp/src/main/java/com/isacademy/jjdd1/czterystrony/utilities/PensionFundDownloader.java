package com.isacademy.jjdd1.czterystrony.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;

import static com.isacademy.jjdd1.czterystrony.utilities.Constants.*;

@Stateless
public class PensionFundDownloader {

    private static Logger log = LoggerFactory.getLogger(PensionFundDownloader.class);

    @Inject
    FileDownloader downloader;

    public void download() {
        try {
            downloader.download(PENSION_FUNDS_RATINGS_ZIP_SOURCE, PENSION_FUNDS_RATINGS_ZIP_PATH);
            log.info("PensionFund ratings zip downloaded from: {} to {}", PENSION_FUNDS_RATINGS_ZIP_SOURCE, PENSION_FUNDS_RATINGS_ZIP_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot download ratings source from: {}", PENSION_FUNDS_RATINGS_ZIP_SOURCE);
        }
    }
}
