package com.isacademy.jjdd1.czterystrony.utilities;

import java.util.Map;

public class TestMapDataFundsCounterPrep {

    public TestMapDataFundsCounterPrep(Map listOfVerifiedFunds) {
        for (int i = 1; i < 10; i++) {
            listOfVerifiedFunds.put("AIG00" + i, 77 + i * 4);
        }
    }
}



