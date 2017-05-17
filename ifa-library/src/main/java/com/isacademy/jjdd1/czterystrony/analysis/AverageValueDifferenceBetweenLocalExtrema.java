package com.isacademy.jjdd1.czterystrony.analysis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AverageValueDifferenceBetweenLocalExtrema {

    private final int DIGITS_AFTER_COMMA = 2;

    public BigDecimal calculate(List<BigDecimal> values) {
        List<BigDecimal> listOfDifferences = getListOfDifferencesBetweenAdjacentValues(values);

        if (listOfDifferences == null || listOfDifferences.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal sum = listOfDifferences.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal listSize = BigDecimal.valueOf(listOfDifferences.size());

        return sum.divide(listSize, DIGITS_AFTER_COMMA, BigDecimal.ROUND_HALF_UP);
    }

    private List<BigDecimal> getListOfDifferencesBetweenAdjacentValues(List<BigDecimal> values) {
        List<BigDecimal> listOfDifferences = new ArrayList<>();

        for (int i = 1; i < values.size(); i++) {
            BigDecimal difference = values.get(i).subtract(values.get(i - 1)).abs();
            listOfDifferences.add(difference);
        }

        return listOfDifferences;
    }
}