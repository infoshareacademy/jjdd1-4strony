package com.isacademy.jjdd1.czterystrony.clients;

import isacademy.jjdd1.czterystrony.reports.persistence.model.statistics.InstrumentStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class StatisticsCache {

    private static final Logger log = LoggerFactory.getLogger(StatisticsCache.class);
    private Set<InstrumentStatistics> statistics = new HashSet<>();

    public void insert(InstrumentStatistics statistics) {
        this.statistics.add(statistics);
        log.info("Added new statistics to cache.");
    }

    public Set<InstrumentStatistics> get() {
        return statistics;
    }

    public boolean isEmpty() {
        return statistics.isEmpty();
    }
}
