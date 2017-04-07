package com.isacademy.jjdd1.czterystrony;


import java.util.Scanner;


public class MainMenu {

        public int MainMenu; {

        System.out.println("Witaj w analizatorze funduszy inwestycyjnych. Wybierz co chcesz zrobić:");
        System.out.println("[1]. Rozpocznij");
        System.out.println("[0]. Wyjdź");

        Scanner answer = new Scanner(System.in);
        int start = answer.nextInt();
        switch (start) {
            case 1:
                MenuOfFunds toMenuOfFunds = new MenuOfFunds();
                break;
            case 0:
                System.out.println("Miłego dnia!");
                break;
            default:
                System.out.println("Błędny wybór.");
        }
    }
}
