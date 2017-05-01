package com.isacademy.jjdd1.czterystrony.initializers;

import com.isacademy.jjdd1.czterystrony.updaters.InvestFundsUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;

@Startup
@Singleton
@DependsOn("DatabaseInitializer")
public class InvestFundsInitializer {

    private static Logger log = LoggerFactory.getLogger(InvestFundsInitializer.class);

    @EJB
    private InvestFundsUpdater investFundsUpdater;

    @PostConstruct
    void initialize() {
        try {
            investFundsUpdater.update();
            log.info("Invest funds initialized.");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot initialize invest funds.");
        }
    }
}
