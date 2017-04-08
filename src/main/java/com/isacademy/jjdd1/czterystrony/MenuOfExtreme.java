package com.isacademy.jjdd1.czterystrony;

import jdk.nashorn.internal.objects.NativeRegExp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Created by piotr on 08.04.17.
 * Sierra
 * Czysty kod. Podrecznik Martin
 */
public class MenuOfExtreme {
    public MenuOfExtreme(InvestFund investFund) {
        System.out.println("Witaj w analizatorze funduszy inwestycyjnych. Wybierz co chcesz zrobić:");
        System.out.println("[1]. Rozpocznij");
        System.out.println("[0]. Wyjdź");

        Scanner answer = new Scanner(System.in);
        int start = answer.nextInt();
        switch (start) {
            case 1:
                new LocalExtremaFinder(investFund, new ExtremaFinderConfigurator(2,2, BigDecimal.TEN, BigDecimal.TEN));
                break;
            case 0:
                GlobalExtremaFinder globalExtremaFinder = new GlobalExtremaFinder(investFund);
                List<Rating> maximumExtremaRatings = globalExtremaFinder.getMaximumExtremaRatings();
                for (Rating maximumExtremaRating : maximumExtremaRatings) {
                    System.out.println("dane rating "+maximumExtremaRating);
                }

                break;
            default:
                System.out.println("Błędny wybór.");
        }
    }

}
