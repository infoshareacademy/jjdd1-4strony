package com.isacademy.jjdd1.czterystrony.technicalanalysis;

import com.isacademy.jjdd1.czterystrony.analysis.MovingAverage;
import com.isacademy.jjdd1.czterystrony.analysis.TimeRange;
import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.model.Rating;
import com.isacademy.jjdd1.czterystrony.repositories.RatingRepository;

import javax.ejb.EJB;
import java.util.List;
import java.util.stream.Collectors;

public class MovingAverageProvider {
    private MovingAverage movingAverage;

    @EJB
    private RatingRepository ratingRepository;

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