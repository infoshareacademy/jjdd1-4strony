package com.isacademy.jjdd1.czterystrony.analysis;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class AvgTimeDiffBtwLocalExt {


    private List<Integer> listOfDifferenceTime = new ArrayList<>();

    private List<LocalDate> listOfDates = new ArrayList<>();


    public long getDaysCount(LocalDate start, LocalDate end) {

        return Math.abs(ChronoUnit.DAYS.between(start, end));
    }

    public List<Integer> calculationDiffMeanTime() {


        for (int i = 0; i < listOfDates.size() - 1; i++) {


            listOfDifferenceTime.add(i, (int) getDaysCount(listOfDates.get(i), listOfDates.get(i + 1)));


        }
        return listOfDifferenceTime;

    }

    public Integer calculateAvgDiffBtnTime(List<Integer> listOfDifferenceTime) {

        if (listOfDifferenceTime == null || listOfDifferenceTime.isEmpty()) {
            return 0;
        }

        int sum = 0;
        for (Integer mark : listOfDifferenceTime) {
            sum += mark;

        }
        System.out.println("Suma tych różnic to: " + sum);
        System.out.println("Średnie różnice między datami " + sum / listOfDifferenceTime.size() + " dni.");

        return sum / listOfDifferenceTime.size();
    }

    public List<Integer> getListOfDifferenceTime() {
        return listOfDifferenceTime;
    }


    public static void main(String[] args) {

        AvgTimeDiffBtwLocalExt avgTimeDiffBtwLocalExt = new AvgTimeDiffBtwLocalExt();
        avgTimeDiffBtwLocalExt.fillTheList();
        System.out.println(avgTimeDiffBtwLocalExt.calculationDiffMeanTime());
        avgTimeDiffBtwLocalExt.calculateAvgDiffBtnTime(avgTimeDiffBtwLocalExt.listOfDifferenceTime);
    }


    public List<LocalDate> fillTheList() {

        listOfDates.add(LocalDate.ofEpochDay(2010 - 02 - 23));
        listOfDates.add(LocalDate.ofEpochDay(2010 - 02 - 25));
        listOfDates.add(LocalDate.ofEpochDay(2010 - 02 - 22));
        listOfDates.add(LocalDate.ofEpochDay(2010 - 02 - 01));
        listOfDates.add(LocalDate.ofEpochDay(2010 - 02 - 23));
        listOfDates.add(LocalDate.ofEpochDay(2010 - 02 - 03));
        listOfDates.add(LocalDate.ofEpochDay(2010 - 02 - 27));

        return listOfDates;
    }

}





