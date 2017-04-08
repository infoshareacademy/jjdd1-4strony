package com.isacademy.jjdd1.czterystrony;

import jdk.nashorn.internal.objects.NativeRegExp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;


public class MenuOfExtreme {
    public MenuOfExtreme(InvestFund investFund) {
        System.out.println("Podaj co chcesz zrobić:");
        System.out.println("[1] Ekstrema lokalne");
        System.out.println("[2] Ekstrema globalne");
        System.out.println("[0] Wyjście");

        Scanner answer = new Scanner(System.in);
        int start = answer.nextInt();
        switch (start) {
            case 1:
                new LocalExtremaFinder(investFund, new ExtremaFinderConfigurator(2,2, BigDecimal.TEN, BigDecimal.TEN));
                break;
            case 2:
                GlobalExtremaFinder globalExtremaFinder = new GlobalExtremaFinder(investFund);
                List<Rating> maximumExtremaRatings = globalExtremaFinder.getMaximumExtremaRatings();
                for (Rating maximumExtremaRating : maximumExtremaRatings) {
                    System.out.println("dane rating "+maximumExtremaRating);
                }
            case 0:
                System.out.println("Miłego dnia!");

                break;
            default:
                System.out.println("Błędny wybór.");
        }
    }

}
