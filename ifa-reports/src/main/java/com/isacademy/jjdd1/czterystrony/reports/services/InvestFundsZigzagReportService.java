package com.isacademy.jjdd1.czterystrony.reports.services;

import com.isacademy.jjdd1.czterystrony.analysis.TimeRange;
import com.isacademy.jjdd1.czterystrony.beanparameters.DayParam;
import com.isacademy.jjdd1.czterystrony.beanparameters.MonthParam;
import com.isacademy.jjdd1.czterystrony.beanparameters.PeriodParam;
import com.isacademy.jjdd1.czterystrony.beanparameters.YearParam;
import isacademy.jjdd1.czterystrony.reports.persistence.model.InvestFundZigzagReport;
import isacademy.jjdd1.czterystrony.reports.persistence.model.ZigzagReportWrapper;
import isacademy.jjdd1.czterystrony.reports.persistence.repositories.InvestFundZigzagReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("reports/v1/zigzag/investfunds")
public class InvestFundsZigzagReportService implements ReportService {

    private static final Logger log = LoggerFactory.getLogger(InvestFundsZigzagReportService.class);

    @Inject
    InvestFundZigzagReportRepository repository;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOverallReport() {
        try {
            List<InvestFundZigzagReport> list = repository.getAll();
            TimeRange timeRange = new TimeRange(LocalDate.now(), LocalDate.now());
            ZigzagReportWrapper wrapper = new ZigzagReportWrapper(list, timeRange);
            log.info("Provided ZigZag report.");
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
            List<InvestFundZigzagReport> list = repository.getInTimeRange(start, end);
            ZigzagReportWrapper wrapper = new ZigzagReportWrapper(list, new TimeRange(start, end));
            log.info("Provided popularity report.");
            return Response.ok(wrapper).build();
        } catch (Throwable e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}