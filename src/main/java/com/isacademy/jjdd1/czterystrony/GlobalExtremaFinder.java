package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;

public class GlobalExtremaFinder extends ExtremaFinder {
    public GlobalExtremaFinder(InvestFund investFund) {
        super(investFund, new ExtremaFinderConfigurator(20,20, BigDecimal.ZERO, BigDecimal.ZERO));
    }
}
