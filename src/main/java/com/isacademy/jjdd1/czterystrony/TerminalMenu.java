package com.isacademy.jjdd1.czterystrony;

import java.io.IOException;
import java.util.List;

public class TerminalMenu {

    public static void main(String[] args) {
        RatingsReader newTestFileReader = new RatingsReader("AGI001.txt");

        try {
            List<String> content = newTestFileReader.getContentList();

            for (String row : content) {
                System.out.println(row);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}