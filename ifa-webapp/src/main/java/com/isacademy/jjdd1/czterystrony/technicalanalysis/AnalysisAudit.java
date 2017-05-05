package com.isacademy.jjdd1.czterystrony.technicalanalysis;

import com.isacademy.jjdd1.czterystrony.analysis.TimeRange;
import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.model.InvestFundStatistics;
import com.isacademy.jjdd1.czterystrony.repositories.StatisticsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class AnalysisAudit {

    private static Logger log = LoggerFactory.getLogger(AnalysisAudit.class);

    @Inject
    StatisticsRepository statisticsRepository;

    @AroundInvoke
    public Object addToStatistics(InvocationContext ic) throws Throwable {
        InvestFund investFund = (InvestFund) ic.getParameters()[0];
        TimeRange timeRange = (TimeRange) ic.getParameters()[1];
        int minSwingLimitInPct = (int) ic.getParameters()[2];

        InvestFundStatistics investFundStatistics = new InvestFundStatistics.Builder()
                .withInvestFund(investFund)
                .withDateFrom(timeRange.getStart())
                .withDateTo(timeRange.getEnd())
                .withZigZag(minSwingLimitInPct)
                .build();

        statisticsRepository.add(investFundStatistics);
        log.info("User {} checked {} fund", investFundStatistics.getUser(), investFundStatistics.getId());
        return ic.proceed();
    }
}
