package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public abstract class ExtremaFinder {
    private InvestFund investFund;
    private ExtremaFinderConfigurator extremaFinderConfigurator;
    private List<Rating> minimumExtrema = new ArrayList<>();
    private List<Rating> maximumExtrema = new ArrayList<>();

    public ExtremaFinder(InvestFund investFund, ExtremaFinderConfigurator extremaFinderConfigurator){
        this.investFund = investFund;
        this.extremaFinderConfigurator = extremaFinderConfigurator;
        this.findExtrema();
    }

    private void findExtrema () {
        List<Rating> ratings = investFund.getAllRatings();
        ListIterator ratingsIterator = ratings.listIterator();
        BigDecimal minCloseValue = null;
        BigDecimal maxCloseValue = null;
        Rating minRating = null;
        Rating maxRating = null;

        while(ratingsIterator.hasNext()) {
            //Rating currentRating = (Rating) ratingsIterator.next();

            int begin = ratingsIterator.previousIndex() - extremaFinderConfigurator.getBackwardDaysSensitivity();
            int end = ratingsIterator.nextIndex() + extremaFinderConfigurator.getForwardDaysSensitivity();

            if (begin < 0) {
                begin = 0;
            }

            if (end > ratings.size()) {
                end = ratings.size();
            }

            for (int i = begin; i <= end; i++) {
                BigDecimal currentCloseValue = ratings.get(i).getCloseValue();

                if(minCloseValue == null || maxCloseValue == null) {
                    minCloseValue = currentCloseValue;
                    maxCloseValue = currentCloseValue;
                }

                if(currentCloseValue.compareTo(maxCloseValue) > 0){
                    maxCloseValue = currentCloseValue;
                    maxRating = new Rating(ratings.get(i).getDate(), ratings.get(i).getCloseValue());
                }

                if(currentCloseValue.compareTo(minCloseValue) < 0){
                    minCloseValue = currentCloseValue;
                    minRating = new Rating(ratings.get(i).getDate(), ratings.get(i).getCloseValue());
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
    }

    public List<Rating> getMinimumExtrema() {
        return minimumExtrema;
    }

    public List<Rating> getMaximumExtrema() {
        return maximumExtrema;
    }


}
