package com.isacademy.jjdd1.czterystrony.analysis;

import com.isacademy.jjdd1.czterystrony.instruments.Rating;
import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;

import java.util.List;
import java.util.stream.Collectors;

public class MovingAverageProvider extends StatisticsProvider {
    private MovingAverage movingAverage;

    public MovingAverageProvider(FinancialInstrument instrument, MovingAverage average) {
        super(instrument);
        this.movingAverage = average;
    }

    public MovingAverageProvider(FinancialInstrument instrument, MovingAverage average, TimeRange timeRange) {
        super(instrument, timeRange);
        this.movingAverage = average;
    }

    public List<Rating> getMovingAverageRatings() {
        return ratings.stream()
                .map(t -> mapRatingToAverage(t))
                .collect(Collectors.toList());
    }

    private Rating mapRatingToAverage(Rating rating) {
        movingAverage.add(rating.getCloseValue());
        return new Rating(rating.getDate(), movingAverage.getAverage());
    }
}