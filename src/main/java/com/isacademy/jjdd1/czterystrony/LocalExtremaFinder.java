package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocalExtremaFinder {
    private List<Rating> ratings;
    private int ratingsCount = 0;
    private LocalExtremaFinderConfigurator localExtremaFinderConfigurator;

    public LocalExtremaFinder(FinancialInstrument financialInstrument, LocalExtremaFinderConfigurator localExtremaFinderConfigurator) {
        this.ratings = financialInstrument.getAllRatings();
        this.ratingsCount = ratings.size();
        this.localExtremaFinderConfigurator = localExtremaFinderConfigurator;
    }

    public List<Rating> getMinimumExtremaRatings() {
        return findExtremaRatings(Extremum.MINIMUM);
    }

    public List<Rating> getMaximumExtremaRatings() {
        return findExtremaRatings(Extremum.MAXIMUM);
    }

    private List<Rating> findExtremaRatings(Extremum extremum) {
        List<Rating> leftShiftedRatings = getShiftedRatings(ratings, -localExtremaFinderConfigurator.getBackwardRatingsSensitivity());
        List<Rating> rightShiftedRatings = getShiftedRatings(ratings, localExtremaFinderConfigurator.getForwardRatingsSensitivity());
        List<Boolean> ratingsComparedToRightShiftedRatings = new ArrayList<>();
        List<Boolean> ratingsComparedToLeftShiftedRatings = new ArrayList<>();
        List<Rating> extremaRatings = new ArrayList<>();

        if (extremum == Extremum.MINIMUM) {
            ratingsComparedToRightShiftedRatings = isEachRatingSmallerThenShiftedEquivalent(ratings, rightShiftedRatings);
            ratingsComparedToLeftShiftedRatings = isEachRatingSmallerThenShiftedEquivalent(ratings, leftShiftedRatings);
        } else {
            ratingsComparedToRightShiftedRatings = isEachRatingGreaterThenShiftedEquivalent(ratings, rightShiftedRatings);
            ratingsComparedToLeftShiftedRatings = isEachRatingGreaterThenShiftedEquivalent(ratings, leftShiftedRatings);
        }

        List<Boolean> extremaSignals = getExtremaSignals(ratingsComparedToRightShiftedRatings, ratingsComparedToLeftShiftedRatings);

        int shift = (ratingsCount - extremaSignals.size()) / 2;

        for (int i = shift; i < ratingsCount - shift; i++) {
            Boolean isExtremum = extremaSignals.get(i - shift);
            if (isExtremum) {
                extremaRatings.add(ratings.get(i));
            }
        }
        return extremaRatings;
    }

    private List<Rating> getShiftedRatings(List<Rating> ratings, int shift) {
        List<Rating> shiftedRatings = new ArrayList<>(ratings);
        Collections.rotate(shiftedRatings, shift);
        int absoluteShift = Math.abs(shift);

        try {
            return shiftedRatings.subList(absoluteShift, ratings.size() - absoluteShift);
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }

    private List<Boolean> isEachRatingSmallerThenShiftedEquivalent(List<Rating> inputList, List<Rating> shiftedList) {
        return compareEachRatingWithShiftedEquivalent(inputList, shiftedList, Extremum.MINIMUM, localExtremaFinderConfigurator.getMinimumExistenceSensitivity());
    }

    private List<Boolean> isEachRatingGreaterThenShiftedEquivalent(List<Rating> inputList, List<Rating> shiftedList) {
        return compareEachRatingWithShiftedEquivalent(inputList, shiftedList, Extremum.MAXIMUM, localExtremaFinderConfigurator.getMaximumExistenceSensitivity());
    }

    private List<Boolean> compareEachRatingWithShiftedEquivalent(List<Rating> inputList, List<Rating> shiftedList, Extremum extremum, BigDecimal extremumExistenceSensitivity) {
        int shift = (inputList.size() - shiftedList.size()) / 2;
        List<Boolean> verificationList = new ArrayList<>();

        for (int i = shift; i < inputList.size() - shift; i++) {
            BigDecimal inputListCloseValue = inputList.get(i).getCloseValue();
            BigDecimal shiftedListCloseValue = shiftedList.get(i - shift).getCloseValue();
            BigDecimal difference = inputListCloseValue.subtract(shiftedListCloseValue);

            if (difference.compareTo(extremumExistenceSensitivity) == extremum.getValue()) {
                verificationList.add(Boolean.TRUE);
            } else {
                verificationList.add(Boolean.FALSE);
            }
        }
        return verificationList;
    }

    private List<Boolean> getExtremaSignals(List<Boolean> firstList, List<Boolean> secondList) {
        List<Boolean> extremaSignals = new ArrayList<>();

        for (int i = 0; i < firstList.size(); i++) {
            if (firstList.get(i) && secondList.get(i)) {
                extremaSignals.add(Boolean.TRUE);
            } else {
                extremaSignals.add(Boolean.FALSE);
            }
        }
        return extremaSignals;
    }
}