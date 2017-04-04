package com.isacademy.jjdd1.czterystrony;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InvestFund {
    private String name;
    private List<Rating> ratings = new ArrayList<>();

    public InvestFund(String name) {
        this.name = name;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public List<Rating> getRatings() {
        return ratings;
    }
}
