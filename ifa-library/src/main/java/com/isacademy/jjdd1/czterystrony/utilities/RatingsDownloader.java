package com.isacademy.jjdd1.czterystrony.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Path;

import static com.isacademy.jjdd1.czterystrony.Constants.*;

@Stateless
public class RatingsDownloader {

    private static Logger log = LoggerFactory.getLogger(RatingsDownloader.class);

    @Inject
    FileDownloader downloader;

    public void download() {
        download(INVEST_FUNDS_RATINGS_ZIP_SOURCE, INVEST_FUNDS_RATINGS_ZIP_PATH);
        download(PENSION_FUNDS_RATINGS_ZIP_SOURCE, PENSION_FUNDS_RATINGS_ZIP_PATH);
    }

    private void download(String source, Path destination) {
        try {
            downloader.download(source, destination);
            log.info("Funds ratings zip downloaded from: {} to {}", source, destination);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot download ratings source from: {}", source);
        }
    }
}
