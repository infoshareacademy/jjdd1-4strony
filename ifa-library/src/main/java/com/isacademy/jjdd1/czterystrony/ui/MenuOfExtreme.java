package com.isacademy.jjdd1.czterystrony.ui;

import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;
import com.isacademy.jjdd1.czterystrony.analysis.GlobalExtremaProvider;
import com.isacademy.jjdd1.czterystrony.analysis.LocalExtremaProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class MenuOfExtreme {
    private final static Logger LOGGER = LoggerFactory.getLogger(MenuOfExtreme.class);

    public MenuOfExtreme(InvestFund fund) {
        System.out.println("Jesteś w funduszu " + fund.getName());
        System.out.println("Wybierz co chcesz zrobić:");
        System.out.println("[1] Ekstrema lokalne");
        System.out.println("[2] Ekstrema globalne");
        System.out.println("[0] Wyjście");

        Scanner answer = new Scanner(System.in);
        int chooseExtreme = answer.nextInt();
        LOGGER.trace("User's choice: {}", chooseExtreme);

        switch (chooseExtreme) {
            case 1:
                LocalExtremaProvider localExtremaProvider = new LocalExtremaProvider(fund);

                System.out.println("Lokalne maksima:");
                for (Rating rating : localExtremaProvider.findExtrema(10)) {
                    System.out.println(rating);
                }
                LOGGER.trace("Succesfully loaded {} extremas", localExtremaProvider.findExtrema(10).size());

                System.out.println("\nLokalne minima:");
                for (Rating rating : localExtremaProvider.findExtrema(10)) {
                    System.out.println(rating);
                }
                LOGGER.trace("Succesfully loaded {} extremas", localExtremaProvider.findExtrema(10).size());

                System.out.println("Jesteś w funduszu " + fund.getName());
                System.out.println("Wybierz co chcesz zrobić:");
                System.out.println("[1] Wybierz nowy fundusz.");
                System.out.println("[2] Powrót");
                System.out.println("[0] Wyjście.");
                Scanner menu = new Scanner(System.in);
                int chooseGoBackFromLocal = menu.nextInt();
                switch (chooseGoBackFromLocal) {
                    case 1:
                        LOGGER.trace("User's choice: {}", chooseGoBackFromLocal);
                        new MenuOfFunds();
                        break;
                    case 2:
                        LOGGER.trace("User's choice: {}", chooseGoBackFromLocal);
                        new MenuOfExtreme(fund);
                        break;
                    case 0:
                        System.out.println("Miłego dnia!");
                        LOGGER.trace("User's choice: {} \nExit", chooseGoBackFromLocal);
                        break;
                    default:
                        System.out.println("Błędny wybór. Wybierz fundusz ponownie:");
                        LOGGER.warn("Wrong choice: {}", chooseGoBackFromLocal);
                        new MenuOfFunds();
                        break;
                }
                break;
            case 2:
                GlobalExtremaProvider globalExtremaProvider = new GlobalExtremaProvider(fund);
                List<Rating> extremaRatings = globalExtremaProvider.getGlobalExtrema();
                for (Rating extremaRating : extremaRatings) {
                    System.out.println("dane rating " + extremaRating);
                }
                System.out.println("Jesteś w funduszu " + fund.getName());
                System.out.println("Wybierz co chcesz zrobić:");
                System.out.println("[1] Wybierz nowy fundusz.");
                System.out.println("[2] Powrót");
                System.out.println("[0] Wyjście.");
                Scanner submenu = new Scanner(System.in);
                int chooseGoBackFromGlobal = submenu.nextInt();
                switch (chooseGoBackFromGlobal) {
                    case 1:
                        LOGGER.trace("User's choice: {}", chooseGoBackFromGlobal);
                        new MenuOfFunds();
                        break;
                    case 2:
                        LOGGER.trace("User's choice: {}", chooseGoBackFromGlobal);
                        new MenuOfExtreme(fund);
                        break;
                    case 0:
                        LOGGER.trace("User's choice: {} \nExit", chooseGoBackFromGlobal);
                        System.out.println("Miłego dnia!");
                        break;
                    default:
                        System.out.println("Błędny wybór. Wybierz fundusz ponownie:");
                        LOGGER.warn("Wrong choice: {}", chooseGoBackFromGlobal);
                        new MenuOfFunds();
                        break;
                }
                break;
            case 0:
                System.out.println("Miłego dnia!");

                break;
            default:
                System.out.println("Błędny wybór, spróbuj jeszcze raz.");
                LOGGER.warn("Wrong choice: {}", chooseExtreme);
                new MenuOfFunds();
        }
    }
}
