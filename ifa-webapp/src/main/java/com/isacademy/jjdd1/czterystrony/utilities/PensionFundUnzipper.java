package com.isacademy.jjdd1.czterystrony.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;

import static com.isacademy.jjdd1.czterystrony.utilities.Constants.PENSION_FUNDS_RATINGS_ZIP_PATH;
import static com.isacademy.jjdd1.czterystrony.utilities.Constants.TMP_PROJECT_FOLDER;

public class PensionFundUnzipper {
    private static Logger log = LoggerFactory.getLogger(RatingsUnzipper.class);

    @Inject
    UnzipUtility unzipUtility;

    public void unzip() {
        try {
            unzipUtility.unzip(PENSION_FUNDS_RATINGS_ZIP_PATH, TMP_PROJECT_FOLDER);
            log.info("PensionFund unzipped to: {}", TMP_PROJECT_FOLDER);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot unzip ofe file: {} to: {}", PENSION_FUNDS_RATINGS_ZIP_PATH, TMP_PROJECT_FOLDER);
        }
    }
}
