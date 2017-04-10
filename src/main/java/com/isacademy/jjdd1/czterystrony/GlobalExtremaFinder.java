package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GlobalExtremaFinder {
    private List<Rating> ratings;

    public GlobalExtremaFinder(FinancialInstrument financialInstrument) {
        this.ratings = new ArrayList<>(financialInstrument.getAllRatings());
    }

    public Rating getGlobalMinimum() {
        return getGlobalExtrema().get(0);
    }

    public Rating getGlobalMaximum() {
        return getGlobalExtrema().get(1);
    }

    public List<Rating> getGlobalExtrema() {
        Rating globalMinimumRating = null;
        Rating globalMaximumRating = null;

        for (Rating rating : ratings) {
            BigDecimal closeValue = rating.getCloseValue();

            if (globalMinimumRating == null || globalMaximumRating == null) {
                globalMinimumRating = rating;
                globalMaximumRating = rating;
            }

            if (closeValue.compareTo(globalMaximumRating.getCloseValue()) > 0) {
                globalMaximumRating = rating;
            }

            if (closeValue.compareTo(globalMinimumRating.getCloseValue()) < 0) {
                globalMinimumRating = rating;
            }
        }
        return Arrays.asList(globalMinimumRating, globalMaximumRating);
    }
}