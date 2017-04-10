package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class MenuOfExtreme {
    public MenuOfExtreme(InvestFund fund) {
        System.out.println("Wybierz co chcesz zrobić:");
        System.out.println("[1] Ekstrema lokalne");
        System.out.println("[2] Ekstrema globalne");
        System.out.println("[0] Wyjście");

        Scanner answer = new Scanner(System.in);
        int chooseExtreme = answer.nextInt();
        switch (chooseExtreme) {
            case 1:
                new LocalExtremaFinder(fund, new LocalExtremaFinderConfigurator(2, 2, BigDecimal.TEN, BigDecimal.TEN));
                break;
            case 2:
                GlobalExtremaFinder globalExtremaFinder = new GlobalExtremaFinder(fund);
                List<Rating> extremaRatings = globalExtremaFinder.getGlobalExtrema();
                for (Rating extremaRating : extremaRatings) {
                    System.out.println("dane rating " + extremaRating);
                }
                System.out.println("Wybierz co chcesz zrobić:");
                System.out.println("[1] Wybierz nowy fundusz.");
                System.out.println("[0] Wyjście.");
                Scanner submenu = new Scanner(System.in);
                int chooseGoBack = submenu.nextInt();
                switch (chooseGoBack) {
                    case 1:
                        new MenuOfFunds();
                        break;
                    case 0:
                        chooseExtreme = 0;
                        System.out.println("Miłego dnia!");
                        break;
                    default:
                        System.out.println("Błędny wybór. Wybierz fundusz ponownie:");
                        new MenuOfFunds();
                        break;
                }
                break;
            case 0:
                System.out.println("Miłego dnia!");

                break;
            default:
                System.out.println("Błędny wybór, spróbuj jeszcze raz.");
                new MenuOfFunds();
        }
    }
}
