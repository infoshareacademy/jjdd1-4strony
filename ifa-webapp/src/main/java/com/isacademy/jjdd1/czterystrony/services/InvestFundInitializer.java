package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.repository.InvestFundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;

@Startup
@Singleton
public class InvestFundInitializer {

    private static Logger log = LoggerFactory.getLogger(InvestFundRepository.class);

    @EJB
    private InvestFundUpdater investFundUpdater;

    void initialize() {
        try {
            investFundUpdater.update();
            log.info("Invest funds initialized.");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot initialize invest funds.");
        }
    }

}
