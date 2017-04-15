package com.isacademy.jjdd1.czterystrony.utilities;

import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LocalExtremaFinder {
    private List<Rating> ratings;
    private int ratingsCount = 0;
    private LocalExtremaFinderConfigurator localExtremaFinderConfigurator;

    public LocalExtremaFinder(FinancialInstrument financialInstrument, LocalExtremaFinderConfigurator localExtremaFinderConfigurator) {
        this.ratings = financialInstrument.getRatings();
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
        List<Rating> leftShiftedRatings = Shifter.shift(ratings, -localExtremaFinderConfigurator.getBackwardRatingsSensitivity());
        List<Rating> rightShiftedRatings = Shifter.shift(ratings, localExtremaFinderConfigurator.getForwardRatingsSensitivity());
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

        List<Boolean> extremaSignals = getExtremaSignals(ratingsComparedToLeftShiftedRatings, ratingsComparedToRightShiftedRatings);

        int shift = (ratingsCount - extremaSignals.size()) / 2;

        for (int i = shift; i < ratingsCount - shift; i++) {
            Boolean isExtremum = extremaSignals.get(i - shift);
            if (isExtremum) {
                extremaRatings.add(ratings.get(i));
            }
        }
        return extremaRatings;
    }

    private List<Boolean> isEachRatingSmallerThenShiftedEquivalent(List<Rating> inputRatings, List<Rating> shiftedRatings) {
        return compareEachRatingWithShiftedEquivalent(inputRatings, shiftedRatings, Extremum.MINIMUM, localExtremaFinderConfigurator.getMinimumExistenceSensitivity());
    }

    private List<Boolean> isEachRatingGreaterThenShiftedEquivalent(List<Rating> inputRatings, List<Rating> shiftedRatings) {
        return compareEachRatingWithShiftedEquivalent(inputRatings, shiftedRatings, Extremum.MAXIMUM, localExtremaFinderConfigurator.getMaximumExistenceSensitivity());
    }

    private List<Boolean> compareEachRatingWithShiftedEquivalent(List<Rating> inputRatings, List<Rating> shiftedRatings, Extremum extremum, BigDecimal extremumExistenceSensitivity) {
        int shift = (inputRatings.size() - shiftedRatings.size()) / 2;
        List<Boolean> verification = new ArrayList<>();

        for (int i = shift; i < inputRatings.size() - shift; i++) {
            BigDecimal inputListCloseValue = inputRatings.get(i).getCloseValue();
            BigDecimal shiftedListCloseValue = shiftedRatings.get(i - shift).getCloseValue();
            BigDecimal difference = inputListCloseValue.subtract(shiftedListCloseValue);

            if (difference.compareTo(extremumExistenceSensitivity) == extremum.getValue()) {
                verification.add(Boolean.TRUE);
            } else {
                verification.add(Boolean.FALSE);
            }
        }
        return verification;
    }

    private <T> List<Boolean> getExtremaSignals(List<T> leftVerification, List<T> rightVerification) {
        return IntStream.range(0, leftVerification.size())
                .mapToObj(t -> leftVerification.get(t).equals(rightVerification.get(t)))
                .collect(Collectors.toList());

//        List<Boolean> extremaSignals = new ArrayList<>();
//
//        for (int i = 0; i < leftVerification.size(); i++) {
//            if (leftVerification.get(i) && rightVerification.get(i)) {
//                extremaSignals.add(Boolean.TRUE);
//            } else {
//                extremaSignals.add(Boolean.FALSE);
//            }
//        }
//        return extremaSignals;
    }
}