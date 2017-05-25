package com.isacademy.jjdd1.czterystrony.technicalanalysis;

import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFundRating;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRatingRepository;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LocalExtremaProvider {
    private final BigDecimal HUNDRED_PCT = BigDecimal.valueOf(100);
    private final int DIGITS_AFTER_COMMA = 2;
    private final int DEFAULT_START_INDEX = 0;
    private int minSwingLimitInPct;
    private List<InvestFundRating> ratings;

    @Inject
    InvestFundRatingRepository ratingRepository;

    public List<InvestFundRating> findExtrema(List<InvestFundRating> ratings, int minSwingLimitInPct) {
        this.ratings = ratings;
        this.minSwingLimitInPct = minSwingLimitInPct;
        boolean swingHigh = false;
        boolean swingLow = false;
        int startIndex = DEFAULT_START_INDEX;
        int endIndex = ratings.size();
        int lowIndex = startIndex;
        int highIndex = startIndex;

        List<InvestFundRating> extrema = new ArrayList<>();

        for (int i = startIndex; i < endIndex; i++) {
            if (currentValueIsGreaterThenLastHighValue(i, highIndex)) {
                highIndex = i;

                if (!swingLow && pctDifferenceBetweenHighAndLowIsGreaterOrEqualLimit(highIndex, lowIndex)) {
                    extrema.add(ratings.get(lowIndex));
                    swingHigh = false;
                    swingLow = true;
                }

                if (swingLow) lowIndex = highIndex;

            } else if (currentValueIsSmallerThenLastLowValue(i, lowIndex)) {
                lowIndex = i;

                if (!swingHigh && pctDifferenceBetweenHighAndLowIsGreaterOrEqualLimit(highIndex, lowIndex)) {
                    extrema.add(ratings.get(highIndex));
                    swingHigh = true;
                    swingLow = false;
                }

                if (swingHigh) highIndex = lowIndex;
            }
        }
        return extrema;
    }

    private boolean currentValueIsGreaterThenLastHighValue(int currentIndex, int highIndex) {
        return closeValueForIndex(currentIndex).subtract(closeValueForIndex(highIndex)).compareTo(BigDecimal.ZERO) == 1;
    }

    private boolean currentValueIsSmallerThenLastLowValue(int currentIndex, int lowIndex) {
        return closeValueForIndex(currentIndex).subtract(closeValueForIndex(lowIndex)).compareTo(BigDecimal.ZERO) == -1;
    }

    private boolean pctDifferenceBetweenHighAndLowIsGreaterOrEqualLimit(int highIndex, int lowIndex) {
        return (closeValueForIndex(highIndex).subtract(closeValueForIndex(lowIndex)))
                .divide(closeValueForIndex(lowIndex), DIGITS_AFTER_COMMA, BigDecimal.ROUND_UP)
                .compareTo(minSwingLimit()) >= 0;
    }

    private BigDecimal closeValueForIndex(int index) {
        return ratings.get(index).getClose();
    }

    private BigDecimal minSwingLimit() {
        return BigDecimal.valueOf(minSwingLimitInPct).divide(HUNDRED_PCT, DIGITS_AFTER_COMMA, BigDecimal.ROUND_UP);
    }
}
