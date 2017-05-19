package com.isacademy.jjdd1.czterystrony.model;

import java.math.BigDecimal;

public class StatisticsDetails {

    private String name;
    private String id;
    private int clicks;
    private int averageDateDifference;
    private BigDecimal averageValueDifference;

    public StatisticsDetails(String name, String id, int clicks, int averageDateDifference, BigDecimal averageValueDifference) {
        this.name = name;
        this.id = id;
        this.clicks = clicks;
        this.averageDateDifference = averageDateDifference;
        this.averageValueDifference = averageValueDifference;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getClicks() {
        return clicks;
    }

    public int getAverageDateDifference() {
        return averageDateDifference;
    }

    public BigDecimal getAverageValueDifference() {
        return averageValueDifference;
    }
}
