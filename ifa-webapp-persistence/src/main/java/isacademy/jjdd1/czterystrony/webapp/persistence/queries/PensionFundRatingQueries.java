package isacademy.jjdd1.czterystrony.webapp.persistence.queries;

public final class PensionFundRatingQueries {
    private PensionFundRatingQueries() {
    }

    //language=MySQL
    public static final String insertFromCsv =
            "LOAD DATA LOCAL INFILE :filePath " +
                    "INTO TABLE PensionFundRating " +
                    "FIELDS TERMINATED BY ',' " +
                    "LINES TERMINATED BY '\r\n' " +
                    "IGNORE 1 LINES " +
                    "(pensionFund_id, @date, open, high, low, close, @notimported) " +
                    "SET date = str_to_date(@date, '%Y%m%d')";
}
