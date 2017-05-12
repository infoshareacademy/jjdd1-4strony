package com.isacademy.jjdd1.czterystrony.analysis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by maciejwolodko on 11.05.17.
 */
public class AvgTimeDiffBtwLocalExt {


    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    //private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static long getDayCount(String start, String end) {
        long diff = -1;
        try {
            Date dateStart = simpleDateFormat.parse(start);
            Date dateEnd = simpleDateFormat.parse(end);

            //time is always 00:00:00 so rounding should help to ignore the missing hour when going from winter to summer time as well as the extra hour in the other direction
            diff = Math.round((dateEnd.getTime() - dateStart.getTime()) / (double) 86400000);
        } catch (Exception e) {
            //handle the exception according to your own situation
        }
        return diff;
    }



/*
    List<LocalDate> listOfDifferenceTime = new ArrayList<>();
    List<LocalDate> listOfDates = new ArrayList<>();


    public List<BigDecimal> calculationDiffMeanTime() {

        ///listOfDates.add(Local);

        //20090619

        for (int i = 0; i < listOfDates.size() - 1; i++) {

            BigDecimal differenceValue;

            listOfDifferenceTime.add(i, differenceValue);

        }
        return listOfDifferenceTime;

    }

    public LocalDate calculateAvgDiffBtnTime(List<LocalDate> listOfDifferenceTime) {
       *//* if (listOfDifferenceTime == null || listOfDifferenceTime.isEmpty()) {
            return BigDecimal.ZERO;
        }*//*

        BigDecimal sum = BigDecimal.valueOf(0.00);
        for (BigDecimal mark : listOfDifferenceTime) {
            sum = sum.add(mark) ;

        }

        System.out.println("Suma tych różnic to: " + sum);
        System.out.println(BigDecimal.valueOf(listOfDifferenceTime.size()));

        return sum.divide(BigDecimal.valueOf(listOfDifferenceTime.size()));
    }*/


    public static void main(String[] args) {
        System.out.println(getDayCount("20160231", "20160305"));
        //System.out.println(getDayCount("22.06.2009","23.06.2009"));
        //20090619


    }


}
