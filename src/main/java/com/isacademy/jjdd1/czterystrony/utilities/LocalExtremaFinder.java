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

public class LocalExtremaFinder {
    private final int DEFAULT_START_INDEX = 0;
    private List<Rating> ratings;
    private int startIndex;
    private int endIndex;

    public LocalExtremaFinder(FinancialInstrument financialInstrument) {
        this.ratings = financialInstrument.getRatings();
        this.startIndex = DEFAULT_START_INDEX;
        this.endIndex = ratings.size();
    }

    public LocalExtremaFinder(FinancialInstrument financialInstrument, LocalDate startDate) {
        this.ratings = financialInstrument.getRatings();
        this.startIndex = findIndexOfClosestDate(startDate, DEFAULT_START_INDEX);
        this.endIndex = ratings.size();
    }

    public LocalExtremaFinder(FinancialInstrument financialInstrument, LocalDate startDate, LocalDate endDate) {
        this.ratings = financialInstrument.getRatings();
        this.startIndex = findIndexOfClosestDate(startDate, DEFAULT_START_INDEX);
        this.endIndex = findIndexOfClosestDate(endDate, ratings.size());
    }

    private int findIndexOfClosestDate(LocalDate date, int defaultIndex) {
        if (date != null) {
            List<LocalDate> dates = ratings.stream()
                    .map(t -> t.getDate())
                    .sorted()
                    .collect(Collectors.toList());

            LocalDate closestDate = DateFinder.findClosestDateInDates(dates, date);

            return dates.indexOf(closestDate);
        } else {
            return defaultIndex;
        }
    }

    public List<Rating> findExtrema(double minSwingPct) {
        boolean swingHigh = false;
        boolean swingLow = false;

        int lowIndex = this.startIndex;
        int highIndex = this.startIndex;

        List<Rating> extrema = new ArrayList<>();

        for (int i = startIndex; i < endIndex; i++) {
            if (isCurrentCloseValueGreaterThenHighCloseValue(i, highIndex)) {
                highIndex = i;

                if (!swingLow &&
                        (ratings.get(highIndex).getCloseValue()
                                .subtract(ratings.get(lowIndex).getCloseValue()))
                                .divide(ratings.get(lowIndex).getCloseValue(), 2, BigDecimal.ROUND_HALF_UP)
                                .compareTo(BigDecimal.valueOf(minSwingPct / 100D)) >= 0) {
                    extrema.add(ratings.get(lowIndex));
                    swingHigh = false;
                    swingLow = true;
                }

                if (swingLow) lowIndex = highIndex;

            } else if (isCurrentCloseValueSmallerThenLowCloseValue(i, lowIndex)) {
                lowIndex = i;

                if (!swingHigh &&
                        (ratings.get(highIndex).getCloseValue()
                                .subtract(ratings.get(lowIndex).getCloseValue()))
                                .divide(ratings.get(lowIndex).getCloseValue(), 2, BigDecimal.ROUND_UP)
                                .compareTo(BigDecimal.valueOf(minSwingPct / 100D)) >= 0) {
                    extrema.add(ratings.get(highIndex));
                    swingHigh = true;
                    swingLow = false;
                }

                if (swingHigh) highIndex = lowIndex;
            }
        }
        return extrema;
    }

    private boolean isCurrentCloseValueGreaterThenHighCloseValue(int currentIndex, int highIndex) {
        return ratings.get(currentIndex).getCloseValue().subtract(ratings.get(highIndex).getCloseValue()).compareTo(BigDecimal.ZERO) == 1;
    }

    private boolean isCurrentCloseValueSmallerThenLowCloseValue(int currentIndex, int lowIndex) {
        return ratings.get(currentIndex).getCloseValue().subtract(ratings.get(lowIndex).getCloseValue()).compareTo(BigDecimal.ZERO) == -1;
    }

    public static void main(String[] args) {
        InvestFundsDao investFundsDao = new InvestFundsDaoTxt();
        InvestFund investFund = investFundsDao.get("AVIVA Malych Spolek");
        List<Rating> ratings = investFund.getRatings();

        System.out.println(ratings.size());

        LocalExtremaFinder localExtremaFinder = new LocalExtremaFinder(investFund);

        List<Rating> extrema = localExtremaFinder.findExtrema(10D);

        System.out.println(extrema.size());

        for (Rating rating : extrema) {
            System.out.println(rating);
        }
    }
}
