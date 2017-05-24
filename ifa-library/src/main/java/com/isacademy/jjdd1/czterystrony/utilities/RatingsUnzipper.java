package com.isacademy.jjdd1.czterystrony.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;

import static com.isacademy.jjdd1.czterystrony.Constants.*;

@Stateless
public class RatingsUnzipper {

    private static Logger log = LoggerFactory.getLogger(RatingsUnzipper.class);

    @Inject
    UnzipUtility unzipUtility;

    public void unzip() {
        try {
            unzipUtility.unzip(FUNDS_RATINGS_ZIP_PATH, TMP_PROJECT_FOLDER);
            log.info("Funds ratings unzipped to: {}", TMP_PROJECT_FOLDER);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot uznip ratings file: {} to: {}", FUNDS_RATINGS_ZIP_PATH, TMP_PROJECT_FOLDER);
        }
    }
}
