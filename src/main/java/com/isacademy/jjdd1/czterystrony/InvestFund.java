package com.isacademy.jjdd1.czterystrony;

import java.util.LinkedList;
import java.util.List;

public class InvestFund {
    private String name;
    private List<Rating> ratings = new LinkedList<>();

    public InvestFund(String name) {
        this.name = name;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public List<Rating> getAllRatings() {
        return ratings;
    }
}
