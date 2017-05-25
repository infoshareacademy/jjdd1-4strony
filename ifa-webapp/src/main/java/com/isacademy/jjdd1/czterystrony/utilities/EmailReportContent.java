package com.isacademy.jjdd1.czterystrony.utilities;

import com.isacademy.jjdd1.czterystrony.model.StatisticsDetails;
import com.isacademy.jjdd1.czterystrony.repositories.StatisticsDetailsRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@Stateless
public class EmailReportContent {

    @Inject
    HtmlTableBuilder htmlTableBuilder;

    @Inject
    StatisticsDetailsRepository repository;

    public String getHtmlContent() {
        List<StatisticsDetails> statisticsDetails = repository.getAll();
        HtmlTableBuilder builder = htmlTableBuilder.setHeaders(Arrays.asList("fundusz", "id", "liczba kliknięć"));
        statisticsDetails.forEach(s -> builder.setRow(Arrays.asList(s.getName(), s.getId(), "" + s.getClicks())));
        return builder.build();
    }
}
