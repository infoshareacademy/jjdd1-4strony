package com.isacademy.jjdd1.czterystrony.technicalanalysis;

import com.isacademy.jjdd1.czterystrony.analysis.MovingAverage;
import com.isacademy.jjdd1.czterystrony.model.Rating;

import java.util.List;
import java.util.stream.Collectors;

public class MovingAverageProvider extends AnalysisProvider {
    private MovingAverage movingAverage;

    public MovingAverageProvider(List<Rating> ratings, MovingAverage average) {
        super(ratings);
        this.movingAverage = average;
    }

    public List<Rating> getMovingAverageRatings() {
        return ratings.stream()
                .map(t -> mapRatingToAverage(t))
                .collect(Collectors.toList());
    }

    private Rating mapRatingToAverage(Rating rating) {
        movingAverage.add(rating.getClose());
        rating.setClose(movingAverage.getAverage());
        return rating;
    }
}