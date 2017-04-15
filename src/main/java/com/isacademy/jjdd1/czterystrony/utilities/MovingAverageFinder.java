package com.isacademy.jjdd1.czterystrony.utilities;

import com.isacademy.jjdd1.czterystrony.instruments.Rating;
import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovingAverageFinder {
    private FinancialInstrument financialInstrument;
    private TimeRange timeRange;
    private List<Rating> movingAverageRatings = new ArrayList<>();

    public MovingAverageFinder(FinancialInstrument financialInstrument, TimeRange timeRange) {
        this.financialInstrument = financialInstrument;
        this.timeRange = timeRange;
    }

    public List<Rating> getMovingAverageRatings() {
//        return getRatingsFromTimeRange().stream()
//                .map()
//
//        List <Rating> ratingsInTimeRange = getRatingsFromTimeRange();

        return movingAverageRatings;
    }

    public List<Rating> getRatingsFromTimeRange() {
        return financialInstrument.getRatings().stream()
                .filter(t -> t.getDate().isAfter(timeRange.getStart()) && t.getDate().isBefore(timeRange.getEnd()))
                .collect(Collectors.toList());
    }
}