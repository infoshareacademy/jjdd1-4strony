package com.isacademy.jjdd1.czterystrony;

import java.util.ArrayList;
import java.util.List;

public class SimpleMovingAverageCalculator {
    private FinancialInstrument financialInstrument;

    private List<Rating> movingAverageRatings = new ArrayList<>();

    public SimpleMovingAverageCalculator(FinancialInstrument financialInstrument) {
        this.financialInstrument = financialInstrument;
    }

    public List<Rating> getMovingAverageRatings() {
        return movingAverageRatings;
    }
}