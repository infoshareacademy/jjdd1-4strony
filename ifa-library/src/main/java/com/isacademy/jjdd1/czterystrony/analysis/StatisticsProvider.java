package com.isacademy.jjdd1.czterystrony.analysis;

import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;

import java.util.List;
import java.util.stream.Collectors;

public abstract class StatisticsProvider {
    protected final int DEFAULT_START_INDEX = 0;
    protected List<Rating> ratings;

    public StatisticsProvider(FinancialInstrument instrument) {
        this.ratings = instrument.getRatings();
    }

    public StatisticsProvider(FinancialInstrument instrument, TimeRange timeRange) {
        this.ratings = instrument.getRatings().stream()
                .filter(t -> t.getDate().isAfter(timeRange.getStart()))
                .filter(t -> t.getDate().isBefore(timeRange.getEnd()))
                .collect(Collectors.toList());
    }
}