package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;

public class GlobalExtremaFinder extends ExtremaFinder {
    public GlobalExtremaFinder(InvestFund investFund) {
        super(investFund, new ExtremaFinderConfigurator(40,40, new BigDecimal(1D), new BigDecimal(1D)));
    }
}