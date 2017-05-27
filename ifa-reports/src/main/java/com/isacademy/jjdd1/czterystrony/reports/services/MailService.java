package com.isacademy.jjdd1.czterystrony.reports.services;

import com.isacademy.jjdd1.czterystrony.reports.mail.ReportSender;
import com.isacademy.jjdd1.czterystrony.reports.mail.ReportTimerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("mail")
public class MailService {

    private static Logger log = LoggerFactory.getLogger(ReportTimerService.class);

    @Inject
    ReportSender reportSender;

    @POST
    @Path("/send")
    public Response send() {
        try {
            reportSender.send();
            log.info("Report sent to 4analysisteam@gmail.com");
            return Response.ok().build();
        } catch (Throwable e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/send")
    @Produces(MediaType.TEXT_HTML)
    public Response send(@FormParam("email") String email) {
        try {
            reportSender.send(email);
            log.info("Report sent to: {}", email);
            return Response.ok("email=" + email).build();
        } catch (Throwable e) {
            e.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
