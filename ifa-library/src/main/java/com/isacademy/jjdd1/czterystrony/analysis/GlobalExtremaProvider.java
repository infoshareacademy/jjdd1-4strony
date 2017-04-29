package com.isacademy.jjdd1.czterystrony.analysis;

import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GlobalExtremaProvider extends StatisticsProvider {

    public GlobalExtremaProvider(FinancialInstrument instrument) {
        super(instrument);
    }

    public GlobalExtremaProvider(FinancialInstrument instrument, TimeRange timeRange) {
        super(instrument, timeRange);
    }

    public Rating getGlobalMinimum() {
        return Collections.min(ratings, Comparator.comparing(Rating::getCloseValue));
    }

    public Rating getGlobalMaximum() {
        return Collections.max(ratings, Comparator.comparing(Rating::getCloseValue));
    }

    public List<Rating> getGlobalExtrema() {
        return Arrays.asList(getGlobalMinimum(), getGlobalMaximum());
    }
}