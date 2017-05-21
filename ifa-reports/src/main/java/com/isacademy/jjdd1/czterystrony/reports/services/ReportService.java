package com.isacademy.jjdd1.czterystrony.reports.services;

import com.isacademy.jjdd1.czterystrony.reports.beanparameters.DayParam;
import com.isacademy.jjdd1.czterystrony.reports.beanparameters.MonthParam;
import com.isacademy.jjdd1.czterystrony.reports.beanparameters.YearParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface ReportService {

    @GET
    @Path("{instruments}/{year}/{month}/{day}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getDailyReport(@BeanParam DayParam day);

    @GET
    @Path("/{year}/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getMonthlyReport(@BeanParam MonthParam month);

    @GET
    @Path("/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getYearlyReport(@BeanParam YearParam year);
}
