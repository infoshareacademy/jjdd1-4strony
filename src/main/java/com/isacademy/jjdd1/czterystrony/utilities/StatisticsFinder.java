package com.isacademy.jjdd1.czterystrony.utilities;

import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;

import java.util.List;
import java.util.stream.Collectors;

public abstract class StatisticsFinder {
    protected final int DEFAULT_START_INDEX = 0;
    protected List<Rating> ratings;

    public StatisticsFinder(FinancialInstrument financialInstrument) {
        this.ratings = financialInstrument.getRatings();
    }

    public StatisticsFinder(FinancialInstrument financialInstrument, TimeRange timeRange) {
        this.ratings = financialInstrument.getRatings().stream()
                .filter(t -> t.getDate().isAfter(timeRange.getStart()))
                .filter(t -> t.getDate().isBefore(timeRange.getEnd()))
                .collect(Collectors.toList());
    }
}
