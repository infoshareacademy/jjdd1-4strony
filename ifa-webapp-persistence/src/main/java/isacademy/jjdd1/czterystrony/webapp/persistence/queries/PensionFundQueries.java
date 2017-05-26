package isacademy.jjdd1.czterystrony.webapp.persistence.queries;

public final class PensionFundQueries {
    private PensionFundQueries() {
    }

    //language=MySQL
    public static final String allWithDetails =
            "SELECT " +
                    "p.name AS name, " +
                    "p.id AS id, " +
                    "tab2.date AS date, " +
                    "tab2.close AS close, " +
                    "tab2.diff AS diff " +
                    "FROM PensionFund AS p JOIN (SELECT r1.pensionFund_id, r1.date, r1.close, " +
                    "ROUND((r1.close - r2.close)/r2.close*100, 2) AS diff " +
                    "FROM (SELECT r.pensionFund_id, r.date, r.close, r.row_number " +
                    "FROM (SELECT PensionFundRating. *, (@i \\:= IF(@pensionFund = PensionFundRating.pensionFund_id, @i +1, " +
                    "IF(@pensionFund \\:= PensionFundRating.pensionFund_id, 1, 1))) AS row_number " +
                    "FROM PensionFundRating CROSS JOIN (SELECT @i \\:= 0, @pensionFund \\:= NULL)c " +
                    "ORDER BY PensionFundRating.pensionFund_id, PensionFundRating.date DESC) AS r " +
                    "WHERE r.row_number <= 2) AS r1 " +
                    "JOIN (SELECT r.pensionFund_id, r.date, r.close, r.row_number " +
                    "FROM (SELECT PensionFundRating. *, (@i \\:= IF(@pensionFund = PensionFundRating.pensionFund_id,@i +1, " +
                    "IF(@pensionFund \\:= PensionFundRating.pensionFund_id, 1, 1))) AS row_number " +
                    "FROM PensionFundRating CROSS JOIN (SELECT @i \\:= 0, @pensionFund \\:= NULL) c " +
                    "ORDER BY PensionFundRating.pensionFund_id, PensionFundRating.date DESC) AS r " +
                    "WHERE r.row_number <= 2) AS r2 ON r1.pensionFund_id = r2.pensionFund_id " +
                    "WHERE r1.row_number = 1 AND r2.row_number = 2) tab2 " +
                    "ON p.id = tab2.pensionFund_id";

}
