package com.isacademy.jjdd1.czterystrony;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Constants {
    private Constants() {
    }

    public static final Path TMP_PROJECT_FOLDER = Paths.get(System.getProperty("java.io.tmpdir")).resolve("4analysis");
    public static final String RATINGS_DATA_FILE_EXTENSION = ".txt";
    public static final int RECORDS_TO_SKIP_IN_RATINGS_FILE = 1;

    public static final String INVEST_FUNDS_RATINGS_ZIP_SOURCE = "http://bossa.pl/pub/fundinwest/omega/omegafun.zip";
    public static final String INVEST_FUNDS_LIST_SOURCE = "http://bossa.pl/pub/fundinwest/omega/omegafun.lst";
    public static final Path INVEST_FUNDS_RATINGS_ZIP_PATH = Paths.get(System.getProperty("java.io.tmpdir")).resolve("omegafun.zip");

    public static final String PENSION_FUNDS_RATINGS_ZIP_SOURCE = "http://bossa.pl/pub/ofe/omega/omegaofe.zip";
    public static final String PENSION_FUNDS_LIST_SOURCE = "http://bossa.pl/pub/ofe/omega/omegaofe.lst";
    public static final Path PENSION_FUNDS_RATINGS_ZIP_PATH = Paths.get(System.getProperty("java.io.tmpdir")).resolve("omegaofe.zip");
}
