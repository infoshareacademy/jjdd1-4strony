package com.isacademy.jjdd1.czterystrony;

import java.util.LinkedList;
import java.util.List;

public class ExtremaFinderAlternative {
    private List<Rating> ratings = new LinkedList<>();
    private int ratingsCount = 0;
    private ExtremaFinderConfigurator extremaFinderConfigurator;
    private List<Rating> minimumExtremaRatings = new LinkedList<>();
    private List<Rating> maximumExtremaRatings = new LinkedList<>();

    public ExtremaFinderAlternative(InvestFund investFund, ExtremaFinderConfigurator extremaFinderConfigurator) {
        this.ratings = investFund.getAllRatings();
        this.ratingsCount = ratings.size();
        this.extremaFinderConfigurator = extremaFinderConfigurator;
        this.findExtrema();
    }

    public void findExtrema() {
        for (int i = 0; i < ratingsCount; i++) {
            int beginOfRange = i - extremaFinderConfigurator.getBackwardDaysSensitivity();
            int endOfRange = i + extremaFinderConfigurator.getForwardDaysSensitivity();
            List<Rating> ratingsSubList = ratings.subList(beginOfRange, endOfRange);

            for (int j = beginOfRange; j <= endOfRange ; j++) {
                if (isInRatingsList(j)) {

                }
            }
        }
    }

    private Boolean isInRatingsList(int index) {
        return index >= 0 && index < ratings.size();
    }

    public List<Rating> getMinimumExtremaRatings() {
        return minimumExtremaRatings;
    }

    public List<Rating> getMaximumExtremaRatings() {
        return maximumExtremaRatings;
    }
}
