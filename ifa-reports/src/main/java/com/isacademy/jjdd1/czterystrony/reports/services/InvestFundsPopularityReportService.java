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
import javax.ws.rs.core.*;
import java.net.URI;
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
    public Response createDailyReport(PopularityReportWrapper reportWrapper, @Context UriInfo uriInfo) {
        try {
            LocalDate reportDate = reportWrapper.getReportDate();

            URI uri = uriInfo.getAbsolutePathBuilder()
                    .path(String.valueOf(reportDate.getYear()))
                    .path(String.valueOf(reportDate.getMonthValue()))
                    .path(String.valueOf(reportDate.getDayOfMonth()))
                    .build();

            List<InvestFundsPopularity> reportEntities = reportWrapper.getPopularityDTOs().stream()
                    .map(r -> PopularityFactory.create(new InvestFundsPopularity(), r, reportDate))
                    .collect(Collectors.toList());

            repository.add(reportEntities);
            String result = "Invest funds popularity daily report created. Report date: " + reportDate;
            log.info(result);
            return Response.created(uri).entity(result).build();

        } catch (Throwable e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOverallReport() {
        try {
            List<InvestFundsPopularity> list = repository.getAll();
            log.info("Provided popularity report.");
            return Response.ok(list).build();
        } catch (Throwable e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getYearlyReport(@BeanParam YearParam year) {
        return getPeriodicReport(year);
    }

    @GET
    @Path("/{year}/{month}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMonthlyReport(@BeanParam MonthParam month) {
        return getPeriodicReport(month);
    }

    @GET
    @Path("/{year}/{month}/{day}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDailyReport(@BeanParam DayParam day) {
        return getPeriodicReport(day);
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
