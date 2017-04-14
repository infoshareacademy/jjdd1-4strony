package com.isacademy.jjdd1.czterystrony.utilities;

import com.isacademy.jjdd1.czterystrony.instruments.Rating;
import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SimpleMovingAverageCalculator {
    private FinancialInstrument financialInstrument;
    private TimeRange timeRange;
    private List<Rating> movingAverageRatings = new ArrayList<>();

    public SimpleMovingAverageCalculator(FinancialInstrument financialInstrument, TimeRange timeRange) {
        this.financialInstrument = financialInstrument;
        this.timeRange = timeRange;
    }

    public List<Rating> getMovingAverageRatings() {

        return movingAverageRatings;
    }

    public Rating ratingAtStart() {
        return ratingAtDate(timeRange.getStart());
    }

    public Rating ratingAtEnd() {
        return ratingAtDate(timeRange.getEnd());
    }

    public Rating ratingAtDate(LocalDate date) {
        return financialInstrument.getRatings().stream()
                .filter(t -> t.getDate().equals(date))
                .findFirst()
                .get();
    }
}