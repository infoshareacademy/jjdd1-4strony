package com.isacademy.jjdd1.czterystrony.ui;

import com.isacademy.jjdd1.czterystrony.database.Statistics;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;
import com.isacademy.jjdd1.czterystrony.utilities.GlobalExtremaProvider;
import com.isacademy.jjdd1.czterystrony.utilities.LocalExtremaProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class MenuOfExtreme {

    private static EntityManager entityManager;
    public MenuOfExtreme(InvestFund fund) {
        System.out.println("Jesteś w funduszu " + fund.getName());
        System.out.println("Wybierz co chcesz zrobić:");
        System.out.println("[1] Ekstrema lokalne");
        System.out.println("[2] Ekstrema globalne");
        System.out.println("[0] Wyjście");

        Scanner answer = new Scanner(System.in);
        int chooseExtreme = answer.nextInt();

        Statistics statistics = new Statistics();
        if (chooseExtreme == 1) {
            statistics.setExtremas("locale");
        } else if (chooseExtreme ==2) {
            statistics.setExtremas("global");
        }
        insertStatistics(statistics);

        switch (chooseExtreme) {
            case 1:
                LocalExtremaProvider localExtremaProvider = new LocalExtremaProvider(fund);

                System.out.println("Lokalne maksima:");
                for (Rating rating : localExtremaProvider.findExtrema(10)) {
                    System.out.println(rating);
                }

                System.out.println("\nLokalne minima:");
                for (Rating rating : localExtremaProvider.findExtrema(10)) {
                    System.out.println(rating);
                }

                System.out.println("Jesteś w funduszu " + fund.getName());
                System.out.println("Wybierz co chcesz zrobić:");
                System.out.println("[1] Wybierz nowy fundusz.");
                System.out.println("[2] Powrót");
                System.out.println("[0] Wyjście.");
                Scanner submenu1 = new Scanner(System.in);
                int chooseGoBack1 = submenu1.nextInt();
                switch (chooseGoBack1) {
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
                int chooseGoBack = submenu.nextInt();
                switch (chooseGoBack) {
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
    private static void insertStatistics(Statistics statistics) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(statistics);
        entityManager.getTransaction().commit();

    }
}
