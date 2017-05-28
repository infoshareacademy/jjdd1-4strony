package com.isacademy.jjdd1.czterystrony.reports.services;

import isacademy.jjdd1.czterystrony.reports.persistence.model.ZigzagReport;
import isacademy.jjdd1.czterystrony.reports.persistence.model.ZigzagReportWrapper;
import isacademy.jjdd1.czterystrony.reports.persistence.repositories.ZigzagReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("reports/v1/zigzag/investfunds")
public class ZigzagReportService {

    private static final Logger log = LoggerFactory.getLogger(InvestFundsPopularityReportService.class);

    @Inject
    ZigzagReportRepository repository;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getZigzagReport() {
        try {
            List<ZigzagReport> list = repository.getAll();
            ZigzagReportWrapper wrapper = new ZigzagReportWrapper(list);
            log.info("Provided ZigZag report.");
            return Response.ok(wrapper).build();
        } catch (Throwable e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}