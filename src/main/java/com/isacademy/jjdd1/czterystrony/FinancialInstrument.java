package com.isacademy.jjdd1.czterystrony;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class FinancialInstrument {
    private String name;
    private Set<Rating> ratings = new TreeSet<>();

    public FinancialInstrument(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public List<Rating> getAllRatings() {
        return new ArrayList<>(ratings);
    }
}