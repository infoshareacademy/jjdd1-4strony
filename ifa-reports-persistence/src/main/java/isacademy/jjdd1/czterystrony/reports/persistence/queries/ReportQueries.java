package isacademy.jjdd1.czterystrony.reports.persistence.queries;

public final class ReportQueries {
    private ReportQueries() {
    }

    //language=MySQL
    public static final String getAllInvestFundPopularity =
            "SELECT " +
                    "stat.INSTRUMENT_NAME AS name, " +
                    "stat.INSTRUMENT_ID AS id, " +
                    "count(stat.INSTRUMENT_ID) AS clicks " +
                    "FROM InvestFundStatistics stat " +
                    "GROUP BY stat.INSTRUMENT_NAME, stat.INSTRUMENT_ID " +
                    "ORDER BY clicks DESC";

    //language=MySQL
    public static final String getAllInvestFundPopularityInTimeRange =
            "SELECT " +
                    "stat.INSTRUMENT_NAME AS name, " +
                    "stat.INSTRUMENT_ID AS id, " +
                    "count(stat.INSTRUMENT_ID) AS clicks " +
                    "FROM InvestFundStatistics stat " +
                    "WHERE stat.DATE_TIME >= :startDateTime AND " +
                    "stat.DATE_TIME <= :endDateTime " +
                    "GROUP BY stat.INSTRUMENT_NAME, stat.INSTRUMENT_ID " +
                    "ORDER BY clicks DESC";
}
