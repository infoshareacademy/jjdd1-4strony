package com.isacademy.jjdd1.czterystrony.reports.services;

import com.isacademy.jjdd1.czterystrony.analysis.TimeRange;
import com.isacademy.jjdd1.czterystrony.beanparameters.*;
import isacademy.jjdd1.czterystrony.reports.persistence.model.InvestFundPopularity;
import isacademy.jjdd1.czterystrony.reports.persistence.model.PopularityWrapper;
import isacademy.jjdd1.czterystrony.reports.persistence.repositories.InvestFundPopularityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.time.LocalDate;
import java.util.List;

@Path("reports/v1/popularity/investfunds")
public class InvestFundsPopularityReportService implements ReportService {

    private static final Logger log = LoggerFactory.getLogger(InvestFundsPopularityReportService.class);

    @Inject
    InvestFundPopularityRepository repository;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOverallReport() {
        try {
            List<InvestFundPopularity> list = repository.getAll();
            TimeRange timeRange = new TimeRange(LocalDate.now(), LocalDate.now());
            PopularityWrapper wrapper = new PopularityWrapper(list, timeRange);
            log.info("Provided popularity report.");
            return Response.ok(wrapper).build();
        } catch (Throwable e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    @GET
    @Path("/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getYearlyReport(@BeanParam YearParam year) {
        return getPeriodicReport(year);
    }

    @Override
    @GET
    @Path("/{year}/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMonthlyReport(@BeanParam MonthParam month) {
        return getPeriodicReport(month);
    }

    @Override
    @GET
    @Path("/{year}/{month}/{day}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDailyReport(@BeanParam DayParam day) {
        return getPeriodicReport(day);
    }

    private Response getPeriodicReport(PeriodParam period) {
        try {
            LocalDate start = period.startDate();
            LocalDate end = period.endDate();
            List<InvestFundPopularity> list = repository.getInTimeRange(start, end);
            PopularityWrapper<InvestFundPopularity> wrapper = new PopularityWrapper(list, new TimeRange(start, end));
            log.info("Provided popularity report.");
            return Response.ok(wrapper).build();
        } catch (Throwable e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
