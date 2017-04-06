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
        ListIterator ratingsIterator = ratings.listIterator();

        while (ratingsIterator.hasNext()) {
            Rating currentRating = (Rating) ratingsIterator.next();

            int begin = ratingsIterator.previousIndex() - extremaFinderConfigurator.getBackwardDaysSensitivity();
            int end = ratingsIterator.nextIndex() + extremaFinderConfigurator.getForwardDaysSensitivity();

            if (begin < 0) {
                begin = 0;
            }

            if (end > ratings.size()) {
                end = ratings.size();
            }

            findExtremaInRange(ratings.subList(begin, end), currentRating);
        }
    }

    private void findExtremaInRange(List<Rating> ratingsRange, Rating currentRating) {
        BigDecimal minCloseValue = null;
        BigDecimal maxCloseValue = null;
        Rating minRating = null;
        Rating maxRating = null;

        for (Rating rating : ratingsRange) {
//                System.out.println(i);
            BigDecimal currentCloseValue = rating.getCloseValue();

            if (minCloseValue == null || maxCloseValue == null) {
                minCloseValue = currentCloseValue;
                maxCloseValue = currentCloseValue;
                minRating = currentRating;
                maxRating = currentRating;
            }

            if (currentCloseValue.compareTo(maxCloseValue) > 0) {
                maxCloseValue = currentCloseValue;
                maxRating = currentRating;
            }

            if (currentCloseValue.compareTo(minCloseValue) < 0) {
                minCloseValue = currentCloseValue;
                minRating = currentRating;
            }
        }

        minimumExtrema.add(minRating);
        maximumExtrema.add(maxRating);
    }
//        int prevDiff = array[0] - array[1];
//        int i=1;
//
//        while(i<array.length-1){
//            int currDiff = 0;
//            int zeroCount = 0;
//            while(currDiff == 0 && i<array.length-1){
//                zeroCount++;
//                i++;
//                currDiff = array[i-1] - array[i];
//            }
//
//            int signCurrDiff = Integer.signum(currDiff);
//            int signPrevDiff = Integer.signum(prevDiff);
//            if( signPrevDiff != signCurrDiff && signCurrDiff != 0){ //signSubDiff==0, the case when prev while ended bcoz of last elem
//                int index = i-1-(zeroCount)/2;
//                if(signPrevDiff == 1){
//                    minimumExtrema.add(index);
//                }else{
//                    maximumExtrema.add(index);
//                }
//            }
//            prevDiff = currDiff;
//        }
//
//        for (Rating rating : investFund.getRatings()) {
//            List<Rating> ratings = investFund.getRatings();
//            ratings.
//
//        }

    public Set<Rating> getMinimumExtrema() {
        return minimumExtrema;
    }

    public Set<Rating> getMaximumExtrema() {
        return maximumExtrema;
    }
}
