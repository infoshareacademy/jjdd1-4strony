package com.isacademy.jjdd1.czterystrony.technicalanalysis;

import com.isacademy.jjdd1.czterystrony.analysis.TimeRange;
import com.isacademy.jjdd1.czterystrony.analysis.MovingAverage;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFund;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.Rating;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.RatingRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class MovingAverageProvider {
    private MovingAverage movingAverage;

    @Inject
    RatingRepository ratingRepository;

    public List<Rating> getMovingAverageRatings(InvestFund investFund, TimeRange timeRange, MovingAverage movingAverage) {
        this.movingAverage = movingAverage;
        List<Rating> ratings = ratingRepository.getByFundInTimeRange(investFund, timeRange);
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