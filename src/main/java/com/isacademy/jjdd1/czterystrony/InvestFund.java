package com.isacademy.jjdd1.czterystrony;

import java.util.List;

public class InvestFund {
    private String name;
    private List<Rating> ratings;

    public InvestFund(String name, List<Rating> ratings) {
        this.name = name;
        this.ratings = ratings;
    }
}
