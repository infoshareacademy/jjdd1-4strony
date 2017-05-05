package com.isacademy.jjdd1.czterystrony.technicalanalysis;

import com.isacademy.jjdd1.czterystrony.model.Rating;

import java.util.List;

public abstract class AnalysisProvider {

    protected List<Rating> ratings;

    AnalysisProvider(List<Rating> ratings) {
        this.ratings = ratings;
    }
}