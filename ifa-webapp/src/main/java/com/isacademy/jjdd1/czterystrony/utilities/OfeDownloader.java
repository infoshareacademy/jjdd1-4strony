package com.isacademy.jjdd1.czterystrony.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;

import static com.isacademy.jjdd1.czterystrony.utilities.Constants.*;

@Stateless
public class OfeDownloader {

    private static Logger log = LoggerFactory.getLogger(OfeDownloader.class);

    @Inject
    FileDownloader downloader;

    public void download() {
        try {
            downloader.download(OFE_RATINGS_ZIP_SOURCE, OFE_RATINGS_ZIP_PATH);
            log.info("Ofe ratings zip downloaded from: {} to {}", OFE_RATINGS_ZIP_SOURCE, OFE_RATINGS_ZIP_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot download ratings source from: {}", OFE_RATINGS_ZIP_SOURCE);
        }
    }
}
