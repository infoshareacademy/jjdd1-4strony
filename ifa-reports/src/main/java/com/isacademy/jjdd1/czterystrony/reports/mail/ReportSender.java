package com.isacademy.jjdd1.czterystrony.reports.mail;

import com.isacademy.jjdd1.czterystrony.CompanyMail;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ReportSender {

    @Inject
    EmailSender emailSender;

    @Inject
    EmailReportContent emailReportContent;

    private final String REPORT_EMAIL_SUBJECT = "4analysis - raporty dzienne";

    public void send() {
        emailSender.send(CompanyMail.ADDRESS, REPORT_EMAIL_SUBJECT, emailReportContent.getHtmlContent());
    }

    public void send(String address) {
        emailSender.send(address, REPORT_EMAIL_SUBJECT, emailReportContent.getHtmlContent());
    }
}
