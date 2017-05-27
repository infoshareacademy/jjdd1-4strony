package com.isacademy.jjdd1.czterystrony.technicalanalysis;

import com.isacademy.jjdd1.czterystrony.analysis.MovingAverage;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFundRating;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class MovingAverageProvider {
    private MovingAverage movingAverage;

    public List<InvestFundRating> get(List<InvestFundRating> ratings, MovingAverage movingAverage) {
        this.movingAverage = movingAverage;
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