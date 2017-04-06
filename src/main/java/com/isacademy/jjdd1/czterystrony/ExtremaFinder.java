package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;
import java.util.*;

public abstract class ExtremaFinder {
    private InvestFund investFund;
    private ExtremaFinderConfigurator extremaFinderConfigurator;

    private Set<Rating> minimumExtrema = new LinkedHashSet<>();
    private Set<Rating> maximumExtrema = new LinkedHashSet<>();

    public ExtremaFinder(InvestFund investFund, ExtremaFinderConfigurator extremaFinderConfigurator){
        this.investFund = investFund;
        this.extremaFinderConfigurator = extremaFinderConfigurator;
        this.findExtrema();
    }


    private void findExtrema() {
        List<Rating> ratings = investFund.getAllRatings();

        Rating prevRating = ratings.get(0);
        Rating currRating = ratings.get(1);

        BigDecimal prevCloseValue = prevRating.getCloseValue();
        BigDecimal currCloseValue = currRating.getCloseValue();

        BigDecimal prevDifference = prevCloseValue.subtract(currCloseValue);

        int i=1;

        while(i < ratings.size()-1){
            BigDecimal currDifference = BigDecimal.ZERO;
            int zeroDifferenceCount = 0;
            while(currDifference.equals(BigDecimal.ZERO) && i < ratings.size()-1){
                zeroDifferenceCount++;
                i++;
                prevRating = ratings.get(i-1);
                currRating = ratings.get(i);

                prevCloseValue = prevRating.getCloseValue();
                currCloseValue = currRating.getCloseValue();

                currDifference = prevCloseValue.subtract(currCloseValue);
            }

            int signCurrDifference = 0;
            int signPrevDifference = 0;

            signCurrDifference = currDifference.compareTo(BigDecimal.ZERO);
            signPrevDifference = prevDifference.compareTo(BigDecimal.ZERO);

            if( signPrevDifference != signCurrDifference && signCurrDifference != 0){
                int index = i - 1- (zeroDifferenceCount)/2;
                if(signPrevDifference == 1){
                    minimumExtrema.add(ratings.get(index));
                }else{
                    maximumExtrema.add(ratings.get(index));
                }
            }
            prevDifference = currDifference;
        }
    }

//    private void findExtrema() {
//        List<Rating> ratings = investFund.getAllRatings();
//        ListIterator ratingsIterator = ratings.listIterator();
//
//        while (ratingsIterator.hasNext()) {
//            Rating currentRating = (Rating) ratingsIterator.next();
//
//            int begin = ratingsIterator.previousIndex() - extremaFinderConfigurator.getBackwardDaysSensitivity();
//            int end = ratingsIterator.nextIndex() + extremaFinderConfigurator.getForwardDaysSensitivity();
//
//            if (begin < 0) {
//                begin = 0;
//            }
//
//            if (end > ratings.size()) {
//                end = ratings.size();
//            }
//
//            findExtremaInRange(ratings.subList(begin, end));
//        }
//    }
//
//    private void findExtremaInRange(List<Rating> ratingsRange) {
//        BigDecimal minCloseValue = null;
//        BigDecimal maxCloseValue = null;
//        Rating minRating = null;
//        Rating maxRating = null;
//
//        for (Rating rating : ratingsRange) {
////                System.out.println(i);
//            BigDecimal currentCloseValue = rating.getCloseValue();
//
//            if (minCloseValue == null || maxCloseValue == null) {
//                minCloseValue = currentCloseValue;
//                maxCloseValue = currentCloseValue;
//                minRating = rating;
//                maxRating = rating;
//            }
//
//            if (currentCloseValue.compareTo(maxCloseValue) > 0) {
//                maxCloseValue = currentCloseValue;
//                maxRating = rating;
//            }
//
//            if (currentCloseValue.compareTo(minCloseValue) < 0) {
//                minCloseValue = currentCloseValue;
//                minRating = rating;
//            }
//        }
//
//        minimumExtrema.add(minRating);
//        maximumExtrema.add(maxRating);
//    }

    public Set<Rating> getMinimumExtrema() {
        return minimumExtrema;
    }

    public Set<Rating> getMaximumExtrema() {
        return maximumExtrema;
    }
}
