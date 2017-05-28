package com.isacademy.jjdd1.czterystrony.clients;

import isacademy.jjdd1.czterystrony.reports.persistence.model.InvestFundZigzagReport;
import isacademy.jjdd1.czterystrony.reports.persistence.model.ZigzagReportWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class InvestFundZigzagReportClient {

    private static final Logger log = LoggerFactory.getLogger(InvestFundZigzagReportClient.class);
    private static final String REPORTS_API_URL =
            "http://4strony-reports-api:8080/api/reports/v1/zigzag/investfunds";

    public List<InvestFundZigzagReport> getAll() {
        return getPopularities(getWebTarget());
    }

    public List<InvestFundZigzagReport> getByPeriod(int year) {
        return getPopularities(getWebTarget(year));
    }

    public List<InvestFundZigzagReport> getByPeriod(int year, int month) {
        return getPopularities(getWebTarget(year, month));
    }

    public List<InvestFundZigzagReport> getByPeriod(int year, int month, int day) {
        return getPopularities(getWebTarget(year, month, day));
    }

    private List<InvestFundZigzagReport> getPopularities(WebTarget target) {
        try {
            Response response = target.request().get();
            ZigzagReportWrapper wrapper = response.readEntity(ZigzagReportWrapper.class);
            response.close();
            return wrapper.getZigzagReportList();
        } catch (Throwable e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private WebTarget getWebTarget() {
        return getWebTarget(REPORTS_API_URL);
    }

    private WebTarget getWebTarget(int year) {
        return getWebTarget(REPORTS_API_URL + "/" + year);
    }

    private WebTarget getWebTarget(int year, int month) {
        return getWebTarget(REPORTS_API_URL + "/" + year + "/" + month);
    }

    private WebTarget getWebTarget(int year, int month, int day) {
        return getWebTarget(REPORTS_API_URL + "/" + year + "/" + month + "/" + day);
    }

    private WebTarget getWebTarget(String url) {
        Client client = ClientBuilder.newClient();
        return client.target(url);
    }
}
