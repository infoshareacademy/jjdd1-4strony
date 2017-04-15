package com.isacademy.jjdd1.czterystrony.utilities;

import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDao;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;
import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ZigZag {
    private final int DEFAULT_START_INDEX = 0;
    private FinancialInstrument financialInstrument;
    private int startIndex;
    private int endIndex;

    public static class Builder {
        private FinancialInstrument financialInstrument;
        private LocalDate startDate;
        private LocalDate endDate;

        public Builder(FinancialInstrument financialInstrument) {
            this.financialInstrument = financialInstrument;
        }

        public Builder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public ZigZag build() {
            return new ZigZag(this);
        }
    }

    public ZigZag(Builder builder) {
        this.financialInstrument = builder.financialInstrument;
        this.startIndex = findIndexOfClosestDate(builder.startDate, DEFAULT_START_INDEX);
        this.endIndex = findIndexOfClosestDate(builder.endDate, financialInstrument.getRatings().size());
    }

    private int findIndexOfClosestDate(LocalDate date, int defaultIndex) {
        if (date != null) {
            List<LocalDate> dates = financialInstrument.getRatings().stream()
                    .map(t -> t.getDate())
                    .sorted()
                    .collect(Collectors.toList());

            LocalDate closestDate = DateFinder.findClosest(dates, date);

            return dates.indexOf(closestDate);
        } else {
            return defaultIndex;
        }
    }

    public List<Rating> findExtrema(double minSwingPct) {
        List<Rating> ratings = financialInstrument.getRatings();

        boolean swingHigh = false;
        boolean swingLow = false;

        int obsLow = this.startIndex;
        int obsHigh = this.startIndex;

        List<Rating> extrema = new ArrayList<>();

        for (int obs = startIndex; obs < endIndex; obs++) {
            if (ratings.get(obs).getCloseValue().subtract(ratings.get(obsHigh).getCloseValue()).compareTo(BigDecimal.ZERO) == 1) {
                obsHigh = obs;

                if (!swingLow &&
                        (ratings.get(obsHigh).getCloseValue()
                                .subtract(ratings.get(obsLow).getCloseValue()))
                                .divide(ratings.get(obsLow).getCloseValue(), 2, BigDecimal.ROUND_HALF_UP)
                                .compareTo(BigDecimal.valueOf(minSwingPct / 100D)) >= 0) {
                    extrema.add(ratings.get(obsLow));  // new swinglow
                    swingHigh = false;
                    swingLow = true;
                }

                if (swingLow) obsLow = obsHigh;

            } else if (ratings.get(obs).getCloseValue().subtract(ratings.get(obsLow).getCloseValue()).compareTo(BigDecimal.ZERO) == -1) {
                obsLow = obs;

                if (!swingHigh &&
                        (ratings.get(obsHigh).getCloseValue()
                                .subtract(ratings.get(obsLow).getCloseValue()))
                                .divide(ratings.get(obsLow).getCloseValue(), 2, BigDecimal.ROUND_UP)
                                .compareTo(BigDecimal.valueOf(minSwingPct / 100D)) >= 0) {
                    extrema.add(ratings.get(obsHigh));  // new swinghigh
                    swingHigh = true;
                    swingLow = false;
                }

                if (swingHigh) obsHigh = obsLow;
            }
        }
        return extrema;
    }

    public static void main(String[] args) {

        InvestFundsDao investFundsDao = new InvestFundsDaoTxt();
        InvestFund investFund = investFundsDao.get("AVIVA Malych Spolek");
        List<Rating> ratings = investFund.getRatings();

        System.out.println(ratings.size());

        ZigZag zigZag = new ZigZag.Builder(investFund)
                .withStartDate(LocalDate.parse("2011-02-17"))
                .withEndDate(LocalDate.parse("2018-12-10"))
                .build();

        List<Rating> extrema = zigZag.findExtrema(20D);

        System.out.println(extrema.size());

        for (Rating rating : extrema) {
            System.out.println(rating);
        }
    }
}
