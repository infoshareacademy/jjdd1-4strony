package com.isacademy.jjdd1.czterystrony.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;

import static com.isacademy.jjdd1.czterystrony.utilities.Constants.FUNDS_RATINGS_ZIP_PATH;
import static com.isacademy.jjdd1.czterystrony.utilities.Constants.OFE_RATINGS_ZIP_PATH;
import static com.isacademy.jjdd1.czterystrony.utilities.Constants.TMP_PROJECT_FOLDER;

public class OfeUnzipper {
    private static Logger log = LoggerFactory.getLogger(RatingsUnzipper.class);

    @Inject
    UnzipUtility unzipUtility;

    public void unzip() {
        try {
            unzipUtility.unzip(OFE_RATINGS_ZIP_PATH, TMP_PROJECT_FOLDER);
            log.info("Ofe unzipped to: {}", TMP_PROJECT_FOLDER);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot unzip ofe file: {} to: {}", OFE_RATINGS_ZIP_PATH, TMP_PROJECT_FOLDER);
        }
    }
}
