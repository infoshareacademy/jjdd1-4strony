package isacademy.jjdd1.czterystrony.webapp.persistence.updaters;

import isacademy.jjdd1.czterystrony.webapp.persistence.factories.PensionFundFactory;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.PensionFund;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.PensionFundRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

import static com.isacademy.jjdd1.czterystrony.Constants.PENSION_FUNDS_LIST_SOURCE;
import static com.isacademy.jjdd1.czterystrony.Constants.RATINGS_DATA_FILE_EXTENSION;

@Stateless
public class PensionFundsUpdater {

    @Inject
    PensionFundRepository pensionFundRepository;

    public void update() throws IOException {
        URL url = new URL(PENSION_FUNDS_LIST_SOURCE);
        URLConnection urlConnection = url.openConnection();
        InputStream stream = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        bufferedReader.lines()
                .filter(record -> containsDataFileExtension(record))
                .map(PensionFundFactory::create)
                .filter(pensionFund -> isNew(pensionFund) || isUpdated(pensionFund))
                .forEach(pensionFund -> addNewOrUpdateExisting(pensionFund));
    }

    private Boolean containsDataFileExtension(String record) {
        return record.matches("(.*)" + RATINGS_DATA_FILE_EXTENSION + "(.*)")
                && !record.regionMatches(33, ".txt", 0, 4);
    }

    private boolean isNew(PensionFund pensionFund) {
        return Objects.isNull(pensionFundRepository.getById(pensionFund.getId()));
    }

    private boolean isUpdated(PensionFund pensionFund) {
        PensionFund retrievedPensionFund = pensionFundRepository.getById(pensionFund.getId());
        return pensionFund.getLastRatingDate().isAfter(retrievedPensionFund.getLastRatingDate());
    }

    private void addNewOrUpdateExisting(PensionFund pensionFund) {
        if (isNew(pensionFund)) {
            pensionFundRepository.add(pensionFund);
        } else {
            PensionFund updatedPensionFund = pensionFundRepository.getById(pensionFund.getId());
            updatedPensionFund.setName(pensionFund.getName());
            updatedPensionFund.setLastRatingDate(pensionFund.getLastRatingDate());
        }
    }
}
