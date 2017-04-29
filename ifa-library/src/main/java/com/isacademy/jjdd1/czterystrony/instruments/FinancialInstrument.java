package com.isacademy.jjdd1.czterystrony.instruments;

import com.isacademy.jjdd1.czterystrony.analysis.TimeRange;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FinancialInstrument {
    private String id;
    private String name;
    private List<Rating> ratings = new ArrayList<>();
    private LocalDate currentRatingDate;
    private BigDecimal currentRatingValue;
    private BigDecimal change;

    public static class Builder {
        private String id;
        private String name;
        private List<Rating> ratings = new ArrayList<>();

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withRatings(List<Rating> ratings) {
            this.ratings = ratings;
            return this;
        }

        public FinancialInstrument build() {
            return new FinancialInstrument(this);
        }
    }

    public FinancialInstrument(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.ratings = builder.ratings;
        Rating currentRating = getCurrentRating();
        this.currentRatingDate = currentRating.getDate();
        this.currentRatingValue = currentRating.getCloseValue();
        BigDecimal previousRatingValue = getPreviousRating().getCloseValue();
        this.change = BigDecimal.valueOf(100L).multiply(currentRatingValue.subtract(previousRatingValue).divide(previousRatingValue, 4, RoundingMode.HALF_UP)).setScale(2);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public List<Rating> getRatingsInTimeRange(TimeRange timeRange) {
        return ratings.stream()
                .filter(t -> t.getDate().isAfter(timeRange.getStart()))
                .filter(t -> t.getDate().isBefore(timeRange.getEnd()))
                .collect(Collectors.toList());
    }

    public Rating getCurrentRating() {
        return Collections.max(ratings, Comparator.comparing(Rating::getDate));
    }

    public LocalDate getCurrentRatingDate() {
        return currentRatingDate;
    }

    public BigDecimal getCurrentRatingValue() {
        return currentRatingValue;
    }

    public Rating getPreviousRating() {
        return ratings.get(ratings.size() - 2);
    }

    public BigDecimal getChange() {
        return change;
    }
}