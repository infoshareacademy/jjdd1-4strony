package com.isacademy.jjdd1.czterystrony.utilities;

import javax.ejb.Stateless;
import javax.inject.Inject;

import static com.isacademy.jjdd1.czterystrony.utilities.Constants.COMPANY_EMAIL_ADDRESS;

@Stateless
public class ReportSender {

    @Inject
    EmailSender emailSender;

    @Inject
    EmailReportContent emailReportContent;

    private final String REPORT_EMAIL_SUBJECT = "4analysis - raporty dzienne";

    public void send() {
        emailSender.send(COMPANY_EMAIL_ADDRESS, REPORT_EMAIL_SUBJECT, emailReportContent.getHtmlContent());
    }
}
