package isacademy.jjdd1.czterystrony.reports.persistence.queries;

public final class ReportQueries {
    private ReportQueries(){
    }

    //language=MySQL
    public static final String getAllInvestFundPopularity =
            "SELECT " +
                    "fund.name AS name, " +
                    "stat.investFund_id AS id, "+
                    "count(stat.investFund_id) as clicks " +
                    "FROM InvestFundStatistics stat, InvestFund fund " +
                    "WHERE stat.investFund_id = fund.id GROUP BY investFund_id";
}
