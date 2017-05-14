package com.isacademy.jjdd1.czterystrony.analysis;

import java.text.SimpleDateFormat;
import java.util.*;


public class AvgTimeDiffBtwLocalExt {

    private List<Integer> listOfDifferenceTime = new ArrayList<>();

    private List<String> listOfDates = new ArrayList<>();

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");


    public static int getDayCount(String start, String end) {
        int diff = -1;
        try {
            Date dateStart = simpleDateFormat.parse(start);
            Date dateEnd = simpleDateFormat.parse(end);

            diff = (int) Math.round((dateEnd.getTime() - dateStart.getTime()) / (double) 86400000);
        } catch (Exception e) {

        }
        return diff;
    }


    public List<Integer> calculationDiffMeanTime() {


        for (int i = 0; i < listOfDates.size() - 1; i++) {

            Integer differenceInPositive;
            Integer differenceValue = getDayCount(listOfDates.get(i), listOfDates.get(i + 1));

            if (differenceValue < 0) {
                differenceInPositive = differenceValue * (-1);
                listOfDifferenceTime.add(i, differenceInPositive);
            } else {

                listOfDifferenceTime.add(i, differenceValue);
            }

        }
        return listOfDifferenceTime;

    }

    public Integer calculateAvgDiffBtnTime(List<Integer> listOfDifferenceTime) {

        if (listOfDifferenceTime == null || listOfDifferenceTime.isEmpty()) {
            return 0;
        }

        int sum = 0;
        for (long mark : listOfDifferenceTime) {
            sum += mark;

        }

        return sum / listOfDifferenceTime.size();
    }


    public List<Integer> getListOfDifferenceTime() {
        return listOfDifferenceTime;
    }

    public List<String> getListOfDates() {
        return listOfDates;
    }

    public static SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }
}





