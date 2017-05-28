package com.isacademy.jjdd1.czterystrony.clients;

import com.isacademy.jjdd1.czterystrony.analysis.AverageTimeDifferenceBetweenLocalExtrema;
import com.isacademy.jjdd1.czterystrony.analysis.AverageValueDifferenceBetweenLocalExtrema;
import com.isacademy.jjdd1.czterystrony.restparameters.DateParam;
import com.isacademy.jjdd1.czterystrony.restparameters.IntegerParam;
import isacademy.jjdd1.czterystrony.reports.persistence.model.statistics.InvestFundStatistics;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFundRating;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRatingRepository;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Stateless
public class InstrumentStatisticsSender {

    private static final Logger log = LoggerFactory.getLogger(InstrumentStatisticsSender.class);

    @Inject
    InstrumentStatisticsClient client;

    @Inject
    InvestFundRepository investFundRepository;

    @Inject
    InvestFundRatingRepository ratingRepository;

    @Inject
    AverageValueDifferenceBetweenLocalExtrema averageValueDifferenceCalculator;

    @Inject
    AverageTimeDifferenceBetweenLocalExtrema averageTimeDifferenceCalculator;

    @Asynchronous
    public void send(List<InvestFundRating> localExtrema,
                     String investFundId,
                     IntegerParam zigZag,
                     DateParam start,
                     DateParam end) {

        LocalDate startDate = determineDate(start, investFundId, s -> ratingRepository.getOldestForFund(s));
        LocalDate endDate = determineDate(end, investFundId, s -> ratingRepository.getNewestForFund(s));

        List<BigDecimal> values = transformRatings(localExtrema, r -> r.getClose());
        List<LocalDate> dates = transformRatings(localExtrema, r -> r.getDate());

        BigDecimal averageValueDifference = averageValueDifferenceCalculator.calculate(values);
        Integer averageTimeDifference = averageTimeDifferenceCalculator.calculate(dates);

        InvestFundStatistics statistics = new InvestFundStatistics.Builder()
                .withInstrumentId(investFundId)
                .withInstrumentName(investFundRepository.getById(investFundId).getName())
                .withZigZag(zigZag.getNumber())
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withAverageDateDifference(averageTimeDifference)
                .withAverageValueDifference(averageValueDifference)
//                .withUser()
                .build();

        client.post(statistics);

        log.info("User {} checked {} fund", null, investFundId);
    }

    private LocalDate determineDate(DateParam dateParam, String investFundId, Function<String, InvestFundRating> function) {
        if (Objects.isNull(dateParam)) {
            return function.apply(investFundId).getDate();
        } else {
            return dateParam.getDate();
        }
    }

    private <T> List<T> transformRatings(List<InvestFundRating> ratings, Function<InvestFundRating, T> function) {
        return ratings.stream()
                .map(function)
                .collect(Collectors.toList());
    }
}
