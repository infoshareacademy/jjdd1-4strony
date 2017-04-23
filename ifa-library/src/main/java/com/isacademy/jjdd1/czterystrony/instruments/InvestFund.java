package com.isacademy.jjdd1.czterystrony.instruments;

import java.util.List;

public class InvestFund extends FinancialInstrument implements Promotable {
    private int priority;
    private String company;

    public static class Builder extends FinancialInstrument.Builder {
        private String company;

        public Builder withCompany(String company) {
            this.company = company;
            return this;
        }
        
        @Override
        public Builder withId(String id) {
            return (Builder) super.withId(id);
        }

        @Override
        public Builder withName(String name) {
            return (Builder) super.withName(name);
        }

        @Override
        public Builder withRatings(List<Rating> ratings) {
            return (Builder) super.withRatings(ratings);
        }

        @Override
        public InvestFund build() {
            return new InvestFund(this);
        }
    }

    public InvestFund(Builder builder) {
        super(builder);
        this.company = builder.company;
        this.priority = 0;
    }

    public String getCompany() {
        return company;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public void promote(int priority) {
        this.priority = (priority > 0) ? -priority : 0;
    }

    @Override
    public String toString() {
        return "Company: " + getCompany() +
                " | Name: " + getName() +
                " | Priority: " + getPriority() +
                " | Number of ratings: " + getRatings().size() +
                " | Zmiana: " + getChange() +
                "\n";
    }
}