package com.isacademy.jjdd1.czterystrony.ui;

import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;
import com.isacademy.jjdd1.czterystrony.utilities.GlobalExtremaFinder;
import com.isacademy.jjdd1.czterystrony.utilities.LocalExtremaFinder;
import com.isacademy.jjdd1.czterystrony.utilities.LocalExtremaFinderConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
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
        LOGGER.trace("User's choice: " + chooseExtreme);

        switch (chooseExtreme) {
            case 1:
                LocalExtremaFinder localExtremaFinder = new LocalExtremaFinder(fund, LocalExtremaFinderConfigurator localFinderConfugirator =  new LocalExtremaFinderConfigurator(30, 30, BigDecimal.valueOf(20), BigDecimal.valueOf(20)));
                LOGGER.debug("Settings of local extreme: "+ );
                System.out.println("Lokalne maksima:");
                for (Rating rating : localExtremaFinder.getMaximumExtremaRatings()) {
                    System.out.println(rating);
                }

                System.out.println("\nLokalne minima:");
                for (Rating rating : localExtremaFinder.getMinimumExtremaRatings()) {
                    System.out.println(rating);
                }

                System.out.println("Jesteś w funduszu " + fund.getName());
                System.out.println("Wybierz co chcesz zrobić:");
                System.out.println("[1] Wybierz nowy fundusz.");
                System.out.println("[2] Powrót");
                System.out.println("[0] Wyjście.");
                Scanner submenu1 = new Scanner(System.in);
                int chooseGoBackFromLocal = submenu1.nextInt();
                switch (chooseGoBackFromLocal) {
                    case 1:
                        new MenuOfFunds();
                        break;
                    case 2:
                        new MenuOfExtreme(fund);
                        break;
                    case 0:
                        chooseExtreme = 0;
                        System.out.println("Miłego dnia!");
                        break;
                    default:
                        System.out.println("Błędny wybór. Wybierz fundusz ponownie:");
                        LOGGER.warn("Wrong choice: " + chooseGoBackFromLocal);
                        new MenuOfFunds();
                        break;
                }
                break;
            case 2:
                GlobalExtremaFinder globalExtremaFinder = new GlobalExtremaFinder(fund);
                List<Rating> extremaRatings = globalExtremaFinder.getGlobalExtrema();
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
                        new MenuOfFunds();
                        break;
                    case 2:
                        new MenuOfExtreme(fund);
                        break;
                    case 0:
                        chooseExtreme = 0;
                        System.out.println("Miłego dnia!");
                        break;
                    default:
                        System.out.println("Błędny wybór. Wybierz fundusz ponownie:");
                        LOGGER.warn("Wrong choice: " + chooseGoBackFromGlobal);
                        new MenuOfFunds();
                        break;
                }
                break;
            case 0:
                System.out.println("Miłego dnia!");

                break;
            default:
                System.out.println("Błędny wybór, spróbuj jeszcze raz.");
                LOGGER.warn("Wrong choice: " + chooseExtreme);
                new MenuOfFunds();
        }
    }
}
