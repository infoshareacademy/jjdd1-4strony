package com.isacademy.jjdd1.czterystrony.ui;

import java.util.concurrent.TimeUnit;

public class TerminalMenu {
    private static String option;
    private static final String EXIT_OPTION = "Q";
    private static final String RETURN_OPTION = "R";

    public static void main(String[] args) {
        displayIntro();
        getInputFromUser("");

        while (isNotExitPressed()) {




            getInputFromUser("Podaj:");
        }
        displayOutro();
    }

    private static void displayIntro() {
        char[] welcome = "... W E L C O M E  I N ...\n\n".toCharArray();

        for (char letter : welcome) {
            System.out.print(letter);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
//        System.exit(1);
        System.out.println("===========================================================");
        System.out.println("|                 InvestFundAnalyzer v1.0                 |");
        System.out.println("|---------------------------------------------------------|");
        System.out.println("| Options:                                                |");
        System.out.println("|  [A]. Choose invest fund                                |");
        System.out.println("|  [Q]. Exit                                              |");
        System.out.println("===========================================================");
    }

    private static void displayOutro() {
        System.out.println("===========================================================");
        System.out.println("|                 IvestFundAnalyzer v1.0                  |");
        System.out.println("|---------------------------------------------------------|");
        System.out.println("|                                                         |");
        System.out.println("|                        Goodbye!                         |");
        System.out.println("|                                                         |");
        System.out.println("===========================================================");
    }

    private static void getInputFromUser(String prompt) {
        UserInput userInput = new UserInput();
        option = userInput.getString(prompt);
    }

    private static boolean isInvalidInput() {
        return true;
    }

    private static boolean isNotExitPressed() {
        return !option.equals(EXIT_OPTION) && !option.equals(EXIT_OPTION.toLowerCase());
    }
}
