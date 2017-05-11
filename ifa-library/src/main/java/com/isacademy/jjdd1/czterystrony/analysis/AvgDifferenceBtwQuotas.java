package com.isacademy.jjdd1.czterystrony.analysis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AvgDifferenceBtwQuotas {


    List<BigDecimal> listOfDifferencesInQuotas = new ArrayList<>();
    List<BigDecimal> listOfQuotas = new ArrayList<>();


    public List<BigDecimal> calculationOfTheDiffInQuotas() {

        listOfQuotas.add(BigDecimal.valueOf(0.00));
        listOfQuotas.add(BigDecimal.valueOf(100.00));
        listOfQuotas.add(BigDecimal.valueOf(50.00));
        listOfQuotas.add(BigDecimal.valueOf(300.00));
        listOfQuotas.add(BigDecimal.valueOf(50.00));
        /*listOfQuotas.add(400.00d);
        listOfQuotas.add(700.00d);
        listOfQuotas.add(600.00d);
      listOfQuotas.add(343.55d);
        listOfQuotas.add(234.55d);
        listOfQuotas.add(667.55d);
        listOfQuotas.add(222.55d);
        listOfQuotas.add(666.55d);
        listOfQuotas.add(897.55d);
        listOfQuotas.add(334.55d);*/

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

    public BigDecimal calculateAverage(List<BigDecimal> listOfDifferencesInQuotas) {
        if (listOfDifferencesInQuotas == null || listOfDifferencesInQuotas.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal sum = BigDecimal.valueOf(0.00);
        for (BigDecimal mark : listOfDifferencesInQuotas) {
            sum = sum.add(mark) ;

        }

        System.out.println("Suma tych różnic to: " + sum);
        System.out.println(BigDecimal.valueOf(listOfDifferencesInQuotas.size()));

        return sum.divide(BigDecimal.valueOf(listOfDifferencesInQuotas.size()));
    }

    public static void main(String[] args) {

        AvgDifferenceBtwQuotas avgDifferenceBtwQuotas = new AvgDifferenceBtwQuotas();

        System.out.println("Tu drukują się elementy avgDifferenceBtwQuotas.calculationOfTheDiffInQuotas()  " + avgDifferenceBtwQuotas.calculationOfTheDiffInQuotas());

        System.out.println("To wartość średniej różnicy kwotowej do wpisania do bazy: " + avgDifferenceBtwQuotas.calculateAverage(avgDifferenceBtwQuotas.listOfDifferencesInQuotas));

    }
}






