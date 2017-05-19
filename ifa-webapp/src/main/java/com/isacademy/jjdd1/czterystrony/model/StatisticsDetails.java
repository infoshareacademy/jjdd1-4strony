package com.isacademy.jjdd1.czterystrony.model;

import java.math.BigDecimal;


public class StatisticsDetails {


    private String name;
    private String id;
    private int clicks;
    private int averageDataDifference;
    private BigDecimal averageValueDifferenceBetweenLocalExtrema;


    public StatisticsDetails(String name, String id, int clicks, int averageDataDifference, BigDecimal averageValueDifferenceBetweenLocalExtrema) {
        this.name = name;
        this.id = id;
        this.clicks = clicks;
        this.averageDataDifference = averageDataDifference;
        this.averageValueDifferenceBetweenLocalExtrema = averageValueDifferenceBetweenLocalExtrema;
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

    public int getAverageDataDifference() {
        return averageDataDifference;
    }

    public BigDecimal getAverageValueDifferenceBetweenLocalExtrema() {
        return averageValueDifferenceBetweenLocalExtrema;
    }

/*public StatisticsDetails(String name, String id, int clicks) {
        this.name = name;
        this.id = id;
        this.clicks = clicks;
    }*/

  /*  public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getClicks() {
        return clicks;
    }*/
}
