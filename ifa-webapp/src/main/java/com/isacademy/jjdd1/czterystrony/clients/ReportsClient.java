package com.isacademy.jjdd1.czterystrony.clients;

import isacademy.jjdd1.czterystrony.reports.persistence.model.InvestFundPopularity;
import isacademy.jjdd1.czterystrony.reports.persistence.model.PopularityWrapper;
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
public class ReportsClient {

    private static final Logger log = LoggerFactory.getLogger(ReportsClient.class);
    private static final String INVEST_FUND_STATISTICS_API_URL = "http://4strony-reports-api:8080/api/reports/v1/popularity/investfunds";

    public List<InvestFundPopularity> getAll() {
        return getPopularities(getWebTarget());
    }

    public List<InvestFundPopularity> getByPeriod(int year) {
        return getPopularities(getWebTarget(year));
    }

    public List<InvestFundPopularity> getByPeriod(int year, int month) {
        return getPopularities(getWebTarget(year, month));
    }

    public List<InvestFundPopularity> getByPeriod(int year, int month, int day) {
        return getPopularities(getWebTarget(year, month, day));
    }

    private List<InvestFundPopularity> getPopularities(WebTarget target) {
        try {
            Response response = target.request().get();
            PopularityWrapper<InvestFundPopularity> wrapper = response.readEntity(PopularityWrapper.class);
            response.close();
            return wrapper.getPopularities();
        } catch (Throwable e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private WebTarget getWebTarget() {
        return getWebTarget(INVEST_FUND_STATISTICS_API_URL);
    }

    private WebTarget getWebTarget(int year) {
        return getWebTarget(INVEST_FUND_STATISTICS_API_URL + "/" + year);
    }

    private WebTarget getWebTarget(int year, int month) {
        return getWebTarget(INVEST_FUND_STATISTICS_API_URL + "/" + year + "/" + month);
    }

    private WebTarget getWebTarget(int year, int month, int day) {
        return getWebTarget(INVEST_FUND_STATISTICS_API_URL + "/" + year + "/" + month + "/" + day);
    }

    private WebTarget getWebTarget(String url) {
        Client client = ClientBuilder.newClient();
        return client.target(url);
    }
}
