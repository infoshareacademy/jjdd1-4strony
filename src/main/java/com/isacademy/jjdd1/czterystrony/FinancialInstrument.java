package com.isacademy.jjdd1.czterystrony;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class FinancialInstrument {
    private String id;
    private String name;
    private Set<Rating> ratings = new TreeSet<>();

    public static class Builder {
        private String id;
        private String name;
        private Set<Rating> ratings;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withRatings(Set<Rating> ratings) {
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
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Rating> getRatings() {
        return new ArrayList<>(ratings);
    }
}