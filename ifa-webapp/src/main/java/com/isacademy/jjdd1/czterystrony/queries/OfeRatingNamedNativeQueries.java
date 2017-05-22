package com.isacademy.jjdd1.czterystrony.queries;

public final class OfeRatingNamedNativeQueries {
    private OfeRatingNamedNativeQueries() {
    }

    //language=MySQL
    public static final String insertFromCsv =
            "LOAD DATA LOCAL INFILE :filePath " +
                    "INTO TABLE OfeRating " +
                    "FIELDS TERMINATED BY ',' " +
                    "LINES TERMINATED BY '\r\n' " +
                    "IGNORE 1 LINES " +
                    "(ofe_id, @date, open, high, low, close, @notimported) " +
                    "SET date = str_to_date(@date, '%Y%m%d')";
}
