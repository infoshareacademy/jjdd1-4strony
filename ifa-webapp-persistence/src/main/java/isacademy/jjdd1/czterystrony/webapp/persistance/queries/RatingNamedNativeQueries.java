package isacademy.jjdd1.czterystrony.webapp.persistance.queries;

public final class RatingNamedNativeQueries {
    private RatingNamedNativeQueries() {
    }

    //language=MySQL
    public static final String insertFromCsv =
            "LOAD DATA LOCAL INFILE :filePath " +
                    "INTO TABLE Rating " +
                    "FIELDS TERMINATED BY ',' " +
                    "LINES TERMINATED BY '\r\n' " +
                    "IGNORE 1 LINES " +
                    "(investFund_id, @date, open, high, low, close, @notimported) " +
                    "SET date = str_to_date(@date, '%Y%m%d')";
}