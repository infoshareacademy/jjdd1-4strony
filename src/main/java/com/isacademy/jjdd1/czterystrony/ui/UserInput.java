package com.isacademy.jjdd1.czterystrony.ui;

import java.util.Scanner;

public class UserInput {
    public int getInt(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNextInt()) {
            System.out.println("Podane wyrażenie nie jest liczbą!\nSpróbuj jeszcze raz:");
            scanner.next();
        }

        return scanner.nextInt();
    }

    public String getString(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNext()) {
            System.out.println("Podane wyrażenie nie jest liczbą!\nSpróbuj jeszcze raz:");
            scanner.next();
        }

        return scanner.next();
    }
}
