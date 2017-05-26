package isacademy.jjdd1.czterystrony.webapp.persistence.initializers;

import isacademy.jjdd1.czterystrony.webapp.persistence.updaters.PensionFundsUpdater;
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
public class PensionFundsInitializer {

    private static final Logger log = LoggerFactory.getLogger(PensionFundsInitializer.class);

    @Inject
    PensionFundsUpdater pensionFundsUpdater;

    @PostConstruct
    void initialize() {
        try {
            pensionFundsUpdater.update();
            log.info("Pension funds initialized.");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Cannot initialize pension funds.");
        }
    }
}
