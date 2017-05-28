package com.isacademy.jjdd1.czterystrony.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

abstract class StatisticsClient<T> {
    private static final String API_TEST_URL = "http://4strony-reports-api:8080/api/test";
    private WebTarget target;
    private StatisticsCache<T> cache;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    void post(WebTarget target, StatisticsCache<T> cache, T statistics) {
        this.target = target;
        this.cache = cache;
        post(statistics);
    }

    private void post(T statistics) {
        try {
            Response response = target.request().post(Entity.json(statistics));
            response.close();
        } catch (Throwable e) {
            if (!cache.get().contains(statistics)) {
                cache.insert(statistics);
            }
        }
    }

    void postCachedData(StatisticsCache<T> cache) {
        if (!cache.isEmpty()) {
            if (apiIsAvailable()) {
                cache.get().forEach(s -> post(s));
                cache.clear();
                log.info("{} emptied and sent to reports module.", cache.getClass().getSimpleName());
                return;
            }
            log.info("REPORTS API is still unavailable.");
            return;
        }
        log.info("{} is empty.", cache.getClass().getSimpleName());
    }

    private boolean apiIsAvailable() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(API_TEST_URL);

        try {
            Response response = target.request().get();
            response.close();
            return response.getStatus() == 200;
        } catch (Throwable e) {
            return false;
        }
    }
}
