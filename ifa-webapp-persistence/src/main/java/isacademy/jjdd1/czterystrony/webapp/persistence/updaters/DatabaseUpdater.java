package isacademy.jjdd1.czterystrony.webapp.persistence.updaters;

import com.isacademy.jjdd1.czterystrony.utilities.RatingsDownloader;
import com.isacademy.jjdd1.czterystrony.utilities.RatingsUnzipper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;

@Stateless
public class DatabaseUpdater {
    private static Logger LOG = LoggerFactory.getLogger(DatabaseUpdater.class);

    @Inject
    RatingsDownloader ratingsDownloader;

    @Inject
    RatingsUnzipper ratingsUnzipper;

    @Inject
    InvestFundsUpdater investFundsUpdater;

    @Inject
    InvestFundRatingsUpdater investFundRatingsUpdater;

    @Inject
    PensionFundsUpdater pensionFundsUpdater;

    @Inject
    PensionFundRatingsUpdater pensionFundRatingsUpdater;

    public void update() {
        ratingsDownloader.download();
        ratingsUnzipper.unzip();
        updateInstruments();
        updateRatings();
    }

    private void updateInstruments() {
        try {
            investFundsUpdater.update();
            LOG.info("Invest funds updated.");
            pensionFundsUpdater.update();
            LOG.info("Pension funds updated.");
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Cannot update instruments.");
        }
    }

    private void updateRatings() {
        try {
            investFundRatingsUpdater.update();
            pensionFundRatingsUpdater.update();
            LOG.info("All ratings updated.");
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Cannot update ratings.");
        }
    }
}
