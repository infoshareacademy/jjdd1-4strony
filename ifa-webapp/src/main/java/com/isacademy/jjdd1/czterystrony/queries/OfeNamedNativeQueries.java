package com.isacademy.jjdd1.czterystrony.queries;

public final class OfeNamedNativeQueries {
    private OfeNamedNativeQueries() {
    }

    //language=MySQL
    public static final String allWithDetails =
            "SELECT " +
                    "o.name AS name, " +
                    "o.id AS id, " +
                    "tab2.date AS date, " +
                    "tab2.close AS close, " +
                    "tab2.diff AS diff " +
                    "FROM Ofe AS o JOIN (SELECT r1.ofe_id, r1.date, r1.close, " +
                    "ROUND((r1.close - r2.close)/r2.close*100, 2) AS diff " +
                    "FROM (SELECT r.ofe_id, r.date, r.close, r.row_number " +
                    "FROM (SELECT Rating. *, (@i \\:= IF(@ofe = Rating.ofe_id, @i +1, " +
                    "IF(@ofe \\:= Rating.ofe_id, 1, 1))) AS row_number " +
                    "FROM Rating CROSS JOIN (SELECT @i \\:= 0, @ofe \\:= NULL)c " +
                    "ORDER BY Rating.ofe_id, Rating.date DESC) AS r " +
                    "WHERE r.row_number <= 2) AS r1 " +
                    "JOIN (SELECT r.ofe_id, r.date, r.close, r.row_number " +
                    "FROM (SELECT Rating. *, (@i \\:= IF(@ofe = Rating.ofe_id,@i +1, " +
                    "IF(@ofe \\:= Rating.ofe_id, 1, 1))) AS row_number " +
                    "FROM Rating CROSS JOIN (SELECT @i \\:= 0, @ofe \\:= NULL) c " +
                    "ORDER BY Rating.ofe_id, Rating.date DESC) AS r " +
                    "WHERE r.row_number <= 2) AS r2 ON r1.ofe_id = r2.ofe_id " +
                    "WHERE r1.row_number = 1 AND r2.row_number = 2) tab2 " +
                    "ON o.id = tab2.ofe_id";
}
