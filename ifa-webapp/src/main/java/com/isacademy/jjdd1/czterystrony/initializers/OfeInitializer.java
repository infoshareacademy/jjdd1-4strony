package com.isacademy.jjdd1.czterystrony.initializers;

import com.isacademy.jjdd1.czterystrony.updaters.OfesUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import java.io.IOException;

@Startup
@Singleton
@DependsOn("DatabaseInitializer")
public class OfeInitializer {

    private static Logger log = LoggerFactory.getLogger(OfeInitializer.class);

    @Inject
    OfesUpdater ofesUpdater;

    @PostConstruct
    void initialize() {
        try {
            ofesUpdater.update();
            log.info("Ofes initialized.");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot initialize ofes.");
        }
    }
}
