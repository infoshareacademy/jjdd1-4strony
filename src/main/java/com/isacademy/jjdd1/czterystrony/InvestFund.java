package com.isacademy.jjdd1.czterystrony;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvestFund {
    private String name;
    private Map<LocalDate, Rating> ratings = new HashMap<>();

    public InvestFund(String name) {
        this.name = name;
    }

    public void addRating(Rating rating) {
        ratings.put(rating.getDate(), rating);
    }

    public Map<LocalDate, Rating> getRatings() {
        return ratings;
    }
}
