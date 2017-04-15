package com.isacademy.jjdd1.czterystrony.utilities;

import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;

import java.util.List;

public abstract class StatisticsFinder {
    protected final int DEFAULT_START_INDEX = 0;
    protected List<Rating> ratings;
    protected int startIndex;
    protected int endIndex;

    public StatisticsFinder(FinancialInstrument financialInstrument) {
        this.ratings = financialInstrument.getRatings();
        this.startIndex = DEFAULT_START_INDEX;
        this.endIndex = ratings.size();
    }

    public StatisticsFinder(FinancialInstrument financialInstrument, TimeRange timeRange) {
        this.ratings = financialInstrument.getRatings();
        this.startIndex = DateFinder.findIndexOfRatingWithClosestDateInRatings(ratings, timeRange.getStart());
        this.endIndex = DateFinder.findIndexOfRatingWithClosestDateInRatings(ratings, timeRange.getEnd());
    }
}
