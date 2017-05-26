package com.isacademy.jjdd1.czterystrony.clients;

import isacademy.jjdd1.czterystrony.reports.persistence.model.statistics.InstrumentStatistics;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class StatisticsCache {

    private List<InstrumentStatistics> statisticsList = new ArrayList<>();

    public void insert(InstrumentStatistics statistics) {
        statisticsList.add(statistics);
    }

    public List<InstrumentStatistics> get() {
        return statisticsList;
    }

    public boolean isEmpty() {
        return statisticsList.isEmpty();
    }
}
