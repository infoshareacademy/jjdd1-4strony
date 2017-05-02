package com.isacademy.jjdd1.czterystrony.technicalanalysis;

import com.isacademy.jjdd1.czterystrony.model.Rating;

import java.util.List;

public abstract class StatisticsProvider {

    protected List<Rating> ratings;

    StatisticsProvider(List<Rating> ratings) {
        this.ratings = ratings;
    }
}