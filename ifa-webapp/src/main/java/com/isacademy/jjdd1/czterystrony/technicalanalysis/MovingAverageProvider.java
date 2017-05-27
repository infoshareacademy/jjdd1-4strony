package com.isacademy.jjdd1.czterystrony.technicalanalysis;

import com.isacademy.jjdd1.czterystrony.analysis.TimeRange;
import com.isacademy.jjdd1.czterystrony.analysis.MovingAverage;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFund;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFundRating;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRatingRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class MovingAverageProvider {
    private MovingAverage movingAverage;

    @Inject
    InvestFundRatingRepository ratingRepository;

    public List<InvestFundRating> getMovingAverageRatings(InvestFund investFund, TimeRange timeRange, MovingAverage movingAverage) {
        this.movingAverage = movingAverage;
        List<InvestFundRating> ratings = ratingRepository.getByFundInTimeRange(investFund.getId(), timeRange);
        return ratings.stream()
                .map(t -> mapRatingToAverage(t))
                .collect(Collectors.toList());
    }

    private InvestFundRating mapRatingToAverage(InvestFundRating rating) {
        movingAverage.add(rating.getClose());
        rating.setClose(movingAverage.getAverage());
        return rating;
    }
}