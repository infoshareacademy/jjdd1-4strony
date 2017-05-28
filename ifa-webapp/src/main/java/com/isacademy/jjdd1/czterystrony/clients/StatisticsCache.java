package com.isacademy.jjdd1.czterystrony.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public abstract class StatisticsCache<T> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private Set<T> statistics = new HashSet<>();

    public void insert(T object) {
        this.statistics.add(object);
        log.info("Added new statistics to {}", this.getClass().getSimpleName());
    }

    public Set<T> get() {
        return statistics;
    }

    public boolean isEmpty() {
        return statistics.isEmpty();
    }

    public void clear() {
        statistics.clear();
    }
}
