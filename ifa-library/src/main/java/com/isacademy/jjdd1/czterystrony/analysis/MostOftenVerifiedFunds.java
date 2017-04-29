package com.isacademy.jjdd1.czterystrony.analysis;

import java.util.Map;
import java.util.TreeMap;

public class MostOftenVerifiedFunds {

    static private Integer countState = 1;

    public Map<String, Integer> counterOfVerifiedFunds = new TreeMap<>();

    public void checkAndcountVerifiedFunds(String fund) {

        Map<String, Integer> listOfVerifiedFunds = new TreeMap<>();

        new TestMapDataFundsCounterPrep(listOfVerifiedFunds);//examplesofdateforthepresentation

        if (listOfVerifiedFunds.containsKey(fund)) {

            listOfVerifiedFunds.replace(fund, listOfVerifiedFunds.get(fund) + countState);

        } else {

            listOfVerifiedFunds.put(fund, countState);
            System.out.println("");
        }

        System.out.println("Najczęściej weryfikowane fundusze:");
        System.out.println("");

        for (String key : listOfVerifiedFunds.keySet())

            System.out.println(key + "-" + listOfVerifiedFunds.get(key));

    }

}