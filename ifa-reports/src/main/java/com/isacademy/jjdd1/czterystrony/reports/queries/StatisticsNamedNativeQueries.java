package com.isacademy.jjdd1.czterystrony.reports.queries;

public final class StatisticsNamedNativeQueries {
    private StatisticsNamedNativeQueries(){
    }

    //language=MySQL
    public static final String getAllStatisticsDetails =
            "SELECT " +
                    "fund.name AS name, " +
                    "stat.investFund_id AS id, "+
                    "count(stat.investFund_id) as clicks " +
                    "FROM InvestFundStatistics stat, InvestFund fund " +
                    "WHERE stat.investFund_id = fund.id GROUP BY investFund_id";
}
