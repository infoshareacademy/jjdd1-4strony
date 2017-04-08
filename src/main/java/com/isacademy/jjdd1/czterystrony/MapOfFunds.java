package com.isacademy.jjdd1.czterystrony;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapOfFunds {


    public static void setMap(Map<Integer, Set<String>> map) {
        map = new HashMap<>();

        Integer[] tab = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43};
        map.put(tab.length, InvestFundDAO.getAllInvestFunds().keySet());
        System.out.println(map);
    }

    public class setMap extends MapOfFunds {
    }
}
