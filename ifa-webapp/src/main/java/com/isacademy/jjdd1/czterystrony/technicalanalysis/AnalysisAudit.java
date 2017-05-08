package com.isacademy.jjdd1.czterystrony.technicalanalysis;

import com.isacademy.jjdd1.czterystrony.model.InvestFundStatistics;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundStatisticsRepository;
import com.isacademy.jjdd1.czterystrony.services.RestIntegerParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class AnalysisAudit {

    private static Logger log = LoggerFactory.getLogger(AnalysisAudit.class);

    @Inject
    InvestFundStatisticsRepository investFundStatisticsRepository;

    @Inject
    InvestFundRepository investFundRepository;

    @AroundInvoke
    public Object addToStatistics(InvocationContext ic) throws Throwable {
        String investFundId = (String) ic.getParameters()[0];
//        TimeRange timeRange = (TimeRange) ic.getParameters()[1];
        RestIntegerParam zigZag = (RestIntegerParam) ic.getParameters()[1];

        InvestFundStatistics investFundStatistics = new InvestFundStatistics.Builder()
                .withInvestFund(investFundRepository.getById(investFundId))
//                .withDateFrom(timeRange.getStart())
//                .withDateTo(timeRange.getEnd())
                .withZigZag(zigZag.getNumber())
                .build();

        investFundStatisticsRepository.add(investFundStatistics);
        log.info("User {} checked {} fund", investFundStatistics.getUser(), investFundStatistics.getId());
        return ic.proceed();
    }
}
