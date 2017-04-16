package com.isacademy.jjdd1.czterystrony.utilities;

import com.isacademy.jjdd1.czterystrony.instruments.Rating;
import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;

import java.util.List;
import java.util.stream.Collectors;

public class MovingAverageProvider extends StatisticsProvider {
    private MovingAverage movingAverage;

    public MovingAverageProvider(FinancialInstrument financialInstrument, MovingAverage movingAverage) {
        super(financialInstrument);
        this.movingAverage = movingAverage;
    }

    public MovingAverageProvider(FinancialInstrument financialInstrument, TimeRange timeRange, MovingAverage movingAverage) {
        super(financialInstrument, timeRange);
        this.movingAverage = movingAverage;
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