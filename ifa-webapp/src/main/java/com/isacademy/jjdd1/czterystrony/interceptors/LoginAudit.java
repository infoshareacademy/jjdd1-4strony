package com.isacademy.jjdd1.czterystrony.interceptors;

import com.isacademy.jjdd1.czterystrony.restparameters.DateParam;
import com.isacademy.jjdd1.czterystrony.restparameters.IntegerParam;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFundRating;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;
import java.util.List;

public class LoginAudit {

    @AroundInvoke
    public Object createStatistics(InvocationContext ic) throws Throwable {
        Object returnedValue = ic.proceed();

        Response response = (Response) returnedValue;
        List<InvestFundRating> localExtrema = (List<InvestFundRating>) response.getEntity();
        String investFundId = (String) ic.getParameters()[0];
        IntegerParam zigZag = (IntegerParam) ic.getParameters()[1];
        DateParam start = (DateParam) ic.getParameters()[2];
        DateParam end = (DateParam) ic.getParameters()[3];

        return returnedValue;
    }
}
