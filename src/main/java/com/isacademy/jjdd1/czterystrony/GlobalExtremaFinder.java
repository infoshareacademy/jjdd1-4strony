package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;

public class GlobalExtremaFinder extends ExtremaFinder {
    public GlobalExtremaFinder(InvestFund investFund) {
        super(investFund, new ExtremaFinderConfigurator(1,1, new BigDecimal(0D), new BigDecimal(0D)));
    }
}