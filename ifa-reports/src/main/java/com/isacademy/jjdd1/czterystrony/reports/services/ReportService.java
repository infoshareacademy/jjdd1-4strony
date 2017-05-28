package com.isacademy.jjdd1.czterystrony.reports.services;

import com.isacademy.jjdd1.czterystrony.beanparameters.DayParam;
import com.isacademy.jjdd1.czterystrony.beanparameters.MonthParam;
import com.isacademy.jjdd1.czterystrony.beanparameters.YearParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface ReportService {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    Response getOverallReport();

    @GET
    @Path("/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getYearlyReport(@BeanParam YearParam year);

    @GET
    @Path("/{year}/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getMonthlyReport(@BeanParam MonthParam month);

    @GET
    @Path("/{year}/{month}/{day}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getDailyReport(@BeanParam DayParam day);
}
