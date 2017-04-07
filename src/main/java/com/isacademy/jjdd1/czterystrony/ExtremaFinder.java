package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class ExtremaFinder {
    private List<Rating> ratings = new LinkedList<>();
    private int ratingsCount = 0;
    private ExtremaFinderConfigurator extremaFinderConfigurator;
    private List<Rating> minimumExtremaRatings = new LinkedList<>();
    private List<Rating> maximumExtremaRatings = new LinkedList<>();

    public ExtremaFinder(InvestFund investFund, ExtremaFinderConfigurator extremaFinderConfigurator) {
        this.ratings = investFund.getAllRatings();
        this.ratingsCount = ratings.size();
        this.extremaFinderConfigurator = extremaFinderConfigurator;
        this.findExtrema();
    }

    private void findExtrema() {
        List<Rating> leftShiftedRatings = getShiftedList(ratings, -extremaFinderConfigurator.getBackwardDaysSensitivity());
        List<Rating> rightShiftedRatings = getShiftedList(ratings, extremaFinderConfigurator.getForwardDaysSensitivity());

        List<Boolean> ratingsComparedToRightShiftedRatings = isEachRatingGreaterThenShiftedEquivalent(ratings, rightShiftedRatings);
        List<Boolean> ratingsComparedToLeftShiftedRatings  = isEachRatingGreaterThenShiftedEquivalent(ratings, leftShiftedRatings);

        List<Boolean> maximumSignalList = getMaximumExtremaSignalList(ratingsComparedToRightShiftedRatings, ratingsComparedToLeftShiftedRatings);

        int shift = (ratingsCount - maximumSignalList.size()) / 2;

        for (int i = shift; i < ratingsCount - shift; i++) {
            Boolean isMaximum = maximumSignalList.get(i - shift);
            if (isMaximum) {
                maximumExtremaRatings.add(ratings.get(i));
            }
        }
    }

    private List<Rating> getShiftedList(List<Rating> list, int shift) {
        List<Rating> shiftedList = new LinkedList<>(list);
        Collections.rotate(shiftedList, shift);
        int absoluteShift = Math.abs(shift);
        return shiftedList.subList(absoluteShift, list.size() - absoluteShift);
    }

    private List<Boolean> isEachRatingGreaterThenShiftedEquivalent(List<Rating> inputList, List<Rating> shiftedList) {
        List<Boolean> verificationList = new LinkedList<>();

        int shift = (inputList.size() - shiftedList.size()) / 2;

        for (int i = shift; i < inputList.size() - shift; i++) {
            BigDecimal inputListCloseValue = inputList.get(i).getCloseValue();
            BigDecimal shiftedListCloseValue = shiftedList.get(i - shift).getCloseValue();
            BigDecimal difference = inputListCloseValue.subtract(shiftedListCloseValue);

            if (difference.compareTo(extremaFinderConfigurator.getMaximumExistenceSensitivity()) == 1) {
                verificationList.add(Boolean.TRUE);
            } else {
                verificationList.add(Boolean.FALSE);
            }
        }
        return verificationList;
    }

    private List<Boolean> getMaximumExtremaSignalList(List<Boolean> firstList, List<Boolean> secondList) {
        List<Boolean> maximaSignalList = new LinkedList<>();

        for (int i = 0; i < firstList.size(); i++) {
            if (firstList.get(i) && secondList.get(i)) {
                maximaSignalList.add(Boolean.TRUE);
            } else {
                maximaSignalList.add(Boolean.FALSE);
            }
        }
        return maximaSignalList;
    }

    public List<Rating> getMinimumExtremaRatings() {
        return minimumExtremaRatings;
    }

    public List<Rating> getMaximumExtremaRatings() {
        return maximumExtremaRatings;
    }
}