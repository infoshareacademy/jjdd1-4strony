package com.isacademy.jjdd1.czterystrony.model;

public class StatisticsDetails {

    private String name;
    private String id;
    private int clicks;

    public StatisticsDetails(String name, String id, int clicks) {
        this.name = name;
        this.id = id;
        this.clicks = clicks;
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
}
