package com.isacademy.jjdd1.czterystrony.reports.services;

import com.isacademy.jjdd1.czterystrony.reports.beanparameters.DayParam;
import com.isacademy.jjdd1.czterystrony.reports.beanparameters.MonthParam;
import com.isacademy.jjdd1.czterystrony.reports.beanparameters.PeriodParam;
import com.isacademy.jjdd1.czterystrony.reports.beanparameters.YearParam;
import com.isacademy.jjdd1.czterystrony.reports.model.InvestFundsPopularity;
import com.isacademy.jjdd1.czterystrony.reports.model.PopularityFactory;
import com.isacademy.jjdd1.czterystrony.reports.model.PopularityReportWrapper;
import com.isacademy.jjdd1.czterystrony.reports.repositories.InvestFundPopularityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Path("reports/v1/popularity/investfunds")
public class InvestFundsPopularityReportService implements ReportService {

    private static Logger log = LoggerFactory.getLogger(InvestFundsPopularityReportService.class);

    @Inject
    InvestFundPopularityRepository repository;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public Response createDailyReport(PopularityReportWrapper reportWrapper) {
        try {
            LocalDate reportDate = reportWrapper.getReportDate();
            List<InvestFundsPopularity> reportEntities = reportWrapper.getPopularityDTOs().stream()
                    .map(r -> PopularityFactory.create(new InvestFundsPopularity(), r, reportDate))
                    .collect(Collectors.toList());

            repository.add(reportEntities);
            String result = "Invest funds popularity report created for date " + reportDate;
            log.info(result);
            return Response.status(Response.Status.CREATED).entity(result).build();
        } catch (Throwable e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{year}/{month}/{day}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDailyReport(@BeanParam DayParam day) {
        return getPeriodicReport(day);
    }

    @GET
    @Path("/{year}/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMonthlyReport(@BeanParam MonthParam month) {
        return getPeriodicReport(month);
    }

    @GET
    @Path("/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getYearlyReport(@BeanParam YearParam year) {
        return getPeriodicReport(year);
    }

    private Response getPeriodicReport(PeriodParam period) {
        try {
            List<InvestFundsPopularity> list = repository.getInTimeRange(period.startDate(), period.endDate());
            log.info("Provided popularity report.");
            return Response.ok(list).build();
        } catch (Throwable e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
