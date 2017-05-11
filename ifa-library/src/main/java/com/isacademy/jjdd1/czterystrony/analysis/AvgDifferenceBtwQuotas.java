package com.isacademy.jjdd1.czterystrony.analysis;

import java.util.ArrayList;
import java.util.List;

public class AvgDifferenceBtwQuotas {


    List<Double> listOfDifferencesInQuotas = new ArrayList<>();
    List<Double> listOfQuotas = new ArrayList<>();


    public List<Double> calculationOfTheDiffInQuotas() {

        listOfQuotas.add(0.00d);
        listOfQuotas.add(100.00d);
        listOfQuotas.add(50.00d);
        listOfQuotas.add(300.00d);
        listOfQuotas.add(50.00d);
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
            double differenceInPositive;
            double differenceValue = listOfQuotas.get(i) - (listOfQuotas.get((i) + nextListElement));

            if (differenceValue < 0) {
                differenceInPositive = differenceValue * (-1);
                listOfDifferencesInQuotas.add(i, differenceInPositive);
            } else {
                listOfDifferencesInQuotas.add(i, differenceValue);
            }

        }
        return listOfDifferencesInQuotas;

    }

    public double calculateAverage(List<Double> listOfDifferencesInQuotas) {
        if (listOfDifferencesInQuotas == null || listOfDifferencesInQuotas.isEmpty()) {
            return 0;
        }

        double sum = 0;
        for (Double mark : listOfDifferencesInQuotas) {
            sum += mark;
        }
        System.out.println("Suma tych różnic to: " + sum);
        return sum / listOfDifferencesInQuotas.size();
    }

    public static void main(String[] args) {

        AvgDifferenceBtwQuotas avgDifferenceBtwQuotas = new AvgDifferenceBtwQuotas();

        System.out.println("Tu drukują się elementy avgDifferenceBtwQuotas.calculationOfTheDiffInQuotas()  " + avgDifferenceBtwQuotas.calculationOfTheDiffInQuotas());

        System.out.println("To wartość średniej różnicy kwotowej do wpisania do bazy: " + avgDifferenceBtwQuotas.calculateAverage(avgDifferenceBtwQuotas.listOfDifferencesInQuotas));

    }
}






