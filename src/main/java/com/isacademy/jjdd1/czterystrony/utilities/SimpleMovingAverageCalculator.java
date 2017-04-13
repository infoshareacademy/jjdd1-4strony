package com.isacademy.jjdd1.czterystrony.utilities;

import com.isacademy.jjdd1.czterystrony.instruments.Rating;
import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;

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