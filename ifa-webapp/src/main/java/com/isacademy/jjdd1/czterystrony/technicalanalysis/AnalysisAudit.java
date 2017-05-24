package com.isacademy.jjdd1.czterystrony.technicalanalysis;

//import Rating;
//import com.isacademy.jjdd1.czterystrony.restparameters.RestDateParam;
//import com.isacademy.jjdd1.czterystrony.restparameters.RestIntegerParam;
//import com.isacademy.jjdd1.czterystrony.updaters.StatisticsUpdater;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.inject.Inject;
//import javax.interceptor.AroundInvoke;
//import javax.interceptor.InvocationContext;
//import java.util.List;

public class AnalysisAudit {

//    private static Logger log = LoggerFactory.getLogger(AnalysisAudit.class);
//
//    @Inject
//    StatisticsUpdater statisticsUpdater;
//
//    @AroundInvoke
//    public Object addToStatistics(InvocationContext ic) throws Throwable {
//        Object returnedValue = ic.proceed();
//
//        List<Rating> localExtrema = (List<Rating>) returnedValue;
//
//        String investFundId = (String) ic.getParameters()[0];
//        RestIntegerParam zigZag = (RestIntegerParam) ic.getParameters()[1];
//        RestDateParam start = (RestDateParam) ic.getParameters()[2];
//        RestDateParam end = (RestDateParam) ic.getParameters()[3];
//
//        statisticsUpdater.update(investFundId, zigZag, start, end, localExtrema);
//
//        log.info("User {} checked {} fund", null, investFundId);
//        return returnedValue;
//    }
}
