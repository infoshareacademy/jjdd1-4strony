package com.isacademy.jjdd1.czterystrony.updaters;

import com.isacademy.jjdd1.czterystrony.analysis.AverageTimeDifferenceBetweenLocalExtrema;
import com.isacademy.jjdd1.czterystrony.analysis.AverageValueDifferenceBetweenLocalExtrema;
import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.model.InvestFundStatistics;
import com.isacademy.jjdd1.czterystrony.model.Rating;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundStatisticsRepository;
import com.isacademy.jjdd1.czterystrony.repositories.RatingRepository;
import com.isacademy.jjdd1.czterystrony.services.RestDateParam;
import com.isacademy.jjdd1.czterystrony.services.RestIntegerParam;
import com.isacademy.jjdd1.czterystrony.users.SessionData;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Stateless
public class StatisticsUpdater {

    @Inject
    InvestFundStatisticsRepository investFundStatisticsRepository;

    @Inject
    InvestFundRepository investFundRepository;

    @Inject
    RatingRepository ratingRepository;

    @Inject
    SessionData sessionData;

    @Inject
    AverageValueDifferenceBetweenLocalExtrema averageValueDifferenceCalculator;

    @Inject
    AverageTimeDifferenceBetweenLocalExtrema averageTimeDifferenceCalculator;

    @Asynchronous
    public void update(String investFundId,
                       RestIntegerParam zigZag,
                       RestDateParam start,
                       RestDateParam end,
                       List<Rating> localExtrema) {

        InvestFund investFund = investFundRepository.getById(investFundId);

        LocalDate startDate;
        LocalDate endDate;

        if (Objects.isNull(start)) {
            startDate = ratingRepository.getOldestForFund(investFund).getDate();
        } else {
            startDate = start.getDate();
        }

        if (Objects.isNull(end)) {
            endDate = ratingRepository.getNewestForFund(investFund).getDate();
        } else {
            endDate = end.getDate();
        }

        List<BigDecimal> values = localExtrema.stream()
                .map(r -> r.getClose())
                .collect(Collectors.toList());

        List<LocalDate> dates = localExtrema.stream()
                .map(r -> r.getDate())
                .collect(Collectors.toList());

        BigDecimal averageValueDifference = averageValueDifferenceCalculator.calculate(values);
        Integer averageTimeDifference = averageTimeDifferenceCalculator.calculate(dates);

        InvestFundStatistics investFundStatistics = new InvestFundStatistics.Builder()
                .withInvestFund(investFund)
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withZigZag(zigZag.getNumber())
                .withAverageValueDifference(averageValueDifference)
                .withAverageDateDifference(averageTimeDifference)
//                .withUser(sessionData.getUser().getEmail())
                .build();

        investFundStatisticsRepository.add(investFundStatistics);
    }
}
