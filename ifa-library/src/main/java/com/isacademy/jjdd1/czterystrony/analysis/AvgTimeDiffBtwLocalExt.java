package com.isacademy.jjdd1.czterystrony.analysis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


public class AvgTimeDiffBtwLocalExt {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    List<Long> listOfDifferencesInTime = new ArrayList<>();
    List<Long> listOfDates = new ArrayList<>();
    AvgTimeDiffBtwLocalExt avgTimeDiffBtwLocalExt = new AvgTimeDiffBtwLocalExt();

    public static long getDayCount(String start, String end) {

        long diff = -1;
        try {
            Date dateStart = simpleDateFormat.parse(start);
            Date dateEnd = simpleDateFormat.parse(end);

            diff = Math.round((dateEnd.getTime() - dateStart.getTime()) / (double) 86400000);
        } catch (Exception e) {
        }
        return diff;
    }

    public static void main(String[] args) {
        System.out.println(getDayCount("20220302", "20170305"));
        //System.out.println(getDayCount("22.06.2009","23.06.2009"));
        //20090619


    }

    public List<Long> calculationOfTheDiffInTime() {

        listOfDates.add((long) 20090619);
        listOfDates.add((long) 20090623);
        listOfDates.add((long) 20090619);

//        long amountDiffofDays;
        for (int i = 0; i < listOfDates.size(); i++) {
            int nextListElement = 1;
            long differenceInPositive;
            long differenceValue = listOfDates.get(i) - (listOfDates.get((i) + nextListElement));

            if(differenceValue < 0){

                differenceInPositive = differenceValue * (-1);
                listOfDifferencesInTime.add(i, differenceInPositive);

            }else{

                listOfDifferencesInTime.add(i, differenceValue);

            }

      /*  avgTimeDiffBtwLocalExt.  getDayCount
        listOfDifferencesInTime.add(i, )*/

        }
        System.out.println(listOfDifferencesInTime.);
        return listOfDifferencesInTime;

    }


}
