package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;
import java.util.*;

public class LocalExtremaFinder {
    private List<Rating> ratings;
    private int ratingsCount = 0;
    private LocalExtremaFinderConfigurator localExtremaFinderConfigurator;
    private List<Rating> minimumExtremaRatings = new LinkedList<>();
    private List<Rating> maximumExtremaRatings = new LinkedList<>();

    public LocalExtremaFinder(FinancialInstrument financialInstrument, LocalExtremaFinderConfigurator localExtremaFinderConfigurator) {
        this.ratings = new ArrayList<>(financialInstrument.getAllRatings());
        this.ratingsCount = ratings.size();
        this.localExtremaFinderConfigurator = localExtremaFinderConfigurator;
        this.findExtrema();
    }

    private void findExtrema() {
        List<Rating> leftShiftedRatings = getShiftedRatings(ratings, -localExtremaFinderConfigurator.getBackwardRatingsSensitivity());
        List<Rating> rightShiftedRatings = getShiftedRatings(ratings, localExtremaFinderConfigurator.getForwardRatingsSensitivity());
        List<Boolean> ratingsComparedToRightShiftedRatings = isEachRatingGreaterThenShiftedEquivalent(ratings, rightShiftedRatings);
        List<Boolean> ratingsComparedToLeftShiftedRatings  = isEachRatingGreaterThenShiftedEquivalent(ratings, leftShiftedRatings);
        List<Boolean> maximumSignalList = getMaximumExtremaSignalList(ratingsComparedToRightShiftedRatings, ratingsComparedToLeftShiftedRatings);

//        List<Boolean> ratingsComparedToRightShiftedRatings = isEachRatingGreaterThenShiftedEquivalent(ratings, rightShiftedRatings);
//        List<Boolean> ratingsComparedToLeftShiftedRatings  = isEachRatingGreaterThenShiftedEquivalent(ratings, leftShiftedRatings);
//        List<Boolean> maximumSignalList = getMaximumExtremaSignalList(ratingsComparedToRightShiftedRatings, ratingsComparedToLeftShiftedRatings);

        int shift = (ratingsCount - maximumSignalList.size()) / 2;

        for (int i = shift; i < ratingsCount - shift; i++) {
            Boolean isMaximum = maximumSignalList.get(i - shift);
            if (isMaximum) {
                maximumExtremaRatings.add(ratings.get(i));
            }
        }
    }

    private List<Rating> getShiftedRatings(List<Rating> list, int shift) {
        List<Rating> shiftedList = new LinkedList<>(list);
        Collections.rotate(shiftedList, shift);
        int absoluteShift = Math.abs(shift);

        try {
            return shiftedList.subList(absoluteShift, list.size() - absoluteShift);
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }

    private List<Boolean> isEachRatingGreaterThenShiftedEquivalent(List<Rating> inputList, List<Rating> shiftedList) {
        int shift = (inputList.size() - shiftedList.size()) / 2;
        List<Boolean> verificationList = new LinkedList<>();

        for (int i = shift; i < inputList.size() - shift; i++) {
            BigDecimal inputListCloseValue = inputList.get(i).getCloseValue();
            BigDecimal shiftedListCloseValue = shiftedList.get(i - shift).getCloseValue();
            BigDecimal difference = inputListCloseValue.subtract(shiftedListCloseValue);

            if (difference.compareTo(localExtremaFinderConfigurator.getMaximumExistenceSensitivity()) == 1) {
                verificationList.add(Boolean.TRUE);
            } else {
                verificationList.add(Boolean.FALSE);
            }
        }
        return verificationList;
    }

    private List<Boolean> isEachRatingSmallerThenShiftedEquivalent(List<Rating> inputList, List<Rating> shiftedList) {
        List<Boolean> isEachRatingSmallerThenShiftedEquivalent = isEachRatingGreaterThenShiftedEquivalent(inputList, shiftedList);
        ListIterator<Boolean> booleanIterator = isEachRatingSmallerThenShiftedEquivalent.listIterator();

        while (booleanIterator.hasNext()) {
            boolean value = booleanIterator.next();
            booleanIterator.set(!value);
        }
        return isEachRatingSmallerThenShiftedEquivalent;
    }

    private List<Boolean> getMaximumExtremaSignalList(List<Boolean> firstList, List<Boolean> secondList) {
        List<Boolean> maximumExtremaShiftedList = new LinkedList<>();

        for (int i = 0; i < firstList.size(); i++) {
            if (firstList.get(i) && secondList.get(i)) {
                maximumExtremaShiftedList.add(Boolean.TRUE);
            } else {
                maximumExtremaShiftedList.add(Boolean.FALSE);
            }
        }
        return maximumExtremaShiftedList;
    }

    public List<Rating> getMinimumExtremaRatings() {
        return minimumExtremaRatings;
    }

    public List<Rating> getMaximumExtremaRatings() {
        return maximumExtremaRatings;
    }
}