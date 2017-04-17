package com.isacademy.jjdd1.czterystrony.utilities;

import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LocalExtremaProvider extends StatisticsProvider {
    private final double HUNDRED_PCT = 100D;
    private final int DIGITS_AFTER_COMMA = 2;

    public LocalExtremaProvider(FinancialInstrument instrument) {
        super(instrument);
    }

    public LocalExtremaProvider(FinancialInstrument instrument, TimeRange timeRange) {
        super(instrument, timeRange);
    }

    public List<Rating> findExtrema(double minSwingLimitInPct) {
        boolean swingHigh = false;
        boolean swingLow = false;
        int startIndex = DEFAULT_START_INDEX;
        int endIndex = ratings.size();
        int lowIndex = startIndex;
        int highIndex = startIndex;

        List<Rating> extrema = new ArrayList<>();

        for (int i = startIndex; i < endIndex; i++) {
            if (currentValueIsGreaterThenLastHighValue(i, highIndex)) {
                highIndex = i;

                if (!swingLow && pctDifferenceBetweenHighAndLowIsGreaterOrEqualLimit(highIndex, lowIndex, minSwingLimitInPct)) {
                    extrema.add(ratings.get(lowIndex));
                    swingHigh = false;
                    swingLow = true;
                }

                if (swingLow) lowIndex = highIndex;

            } else if (currentValueIsSmallerThenLastLowValue(i, lowIndex)) {
                lowIndex = i;

                if (!swingHigh && pctDifferenceBetweenHighAndLowIsGreaterOrEqualLimit(highIndex, lowIndex, minSwingLimitInPct)) {
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
        return ratings.get(currentIndex).getCloseValue().subtract(ratings.get(highIndex).getCloseValue()).compareTo(BigDecimal.ZERO) == 1;
    }

    private boolean currentValueIsSmallerThenLastLowValue(int currentIndex, int lowIndex) {
        return ratings.get(currentIndex).getCloseValue().subtract(ratings.get(lowIndex).getCloseValue()).compareTo(BigDecimal.ZERO) == -1;
    }

    private boolean pctDifferenceBetweenHighAndLowIsGreaterOrEqualLimit(int highIndex, int lowIndex, double minSwingLimitInPct) {
        return (ratings.get(highIndex).getCloseValue()
                .subtract(ratings.get(lowIndex).getCloseValue()))
                .divide(ratings.get(lowIndex).getCloseValue(), DIGITS_AFTER_COMMA, BigDecimal.ROUND_UP)
                .compareTo(BigDecimal.valueOf(minSwingLimitInPct / HUNDRED_PCT)) >= 0;
    }
}
