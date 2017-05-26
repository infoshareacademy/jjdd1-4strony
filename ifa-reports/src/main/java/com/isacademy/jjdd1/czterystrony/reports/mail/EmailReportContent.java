package com.isacademy.jjdd1.czterystrony.reports.mail;

import isacademy.jjdd1.czterystrony.reports.persistence.model.InvestFundPopularity;
import isacademy.jjdd1.czterystrony.reports.persistence.repositories.InvestFundPopularityRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@Stateless
public class EmailReportContent {

    @Inject
    HtmlTableBuilder htmlTableBuilder;

    @Inject
    InvestFundPopularityRepository repository;

    public String getHtmlContent() {
        List<InvestFundPopularity> fundPopularities = repository.getAll();
        HtmlTableBuilder builder = htmlTableBuilder.setHeaders(Arrays.asList("fundusz", "id", "liczba kliknięć"));
        fundPopularities.forEach(s -> builder.setRow(Arrays.asList(s.getInstrumentName(), s.getInstrumentId(), "" + s.getClicks())));
        return builder.build();
    }
}
