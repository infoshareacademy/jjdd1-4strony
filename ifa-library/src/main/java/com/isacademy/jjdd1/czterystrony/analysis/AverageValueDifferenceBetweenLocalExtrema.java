package com.isacademy.jjdd1.czterystrony.analysis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AverageValueDifferenceBetweenLocalExtrema {

    private List<BigDecimal> listOfDifferencesInQuotas = new ArrayList<>();
    private List<BigDecimal> listOfQuotas = new ArrayList<>();

    public List<BigDecimal> calculationOfTheDiffInQuotas() {
        for (int i = 0; i < listOfQuotas.size() - 1; i++) {
            int nextListElement = 1;
            BigDecimal differenceInPositive;
            BigDecimal differenceValue;
            differenceValue = listOfQuotas.get(i).subtract(listOfQuotas.get((i) + nextListElement));

            if (differenceValue.compareTo(BigDecimal.ZERO) < 0) {
                differenceInPositive = differenceValue.abs();
                listOfDifferencesInQuotas.add(i, differenceInPositive);
            } else {
                listOfDifferencesInQuotas.add(i, differenceValue);
            }
        }
        return listOfDifferencesInQuotas;
    }

    public BigDecimal calculate(List<BigDecimal> listOfDifferencesInQuotas) {
        if (listOfDifferencesInQuotas == null || listOfDifferencesInQuotas.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal mark : listOfDifferencesInQuotas) {
            sum = sum.add(mark);
        }

        return sum.divide(BigDecimal.valueOf(listOfDifferencesInQuotas.size()));
    }
}






