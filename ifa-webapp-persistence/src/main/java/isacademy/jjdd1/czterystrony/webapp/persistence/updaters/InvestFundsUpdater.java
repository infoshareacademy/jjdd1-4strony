package isacademy.jjdd1.czterystrony.webapp.persistence.updaters;

import isacademy.jjdd1.czterystrony.webapp.persistence.factories.InvestFundFactory;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFund;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

import static com.isacademy.jjdd1.czterystrony.Constants.*;

@Stateless
public class InvestFundsUpdater {

    @Inject
    InvestFundRepository repository;

    public void update() throws IOException {
        URL url = new URL(INVEST_FUNDS_LIST_SOURCE);
        URLConnection urlConnection = url.openConnection();
        InputStream stream = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        bufferedReader.lines()
                .filter(record -> containsDataFileExtension(record))
                .map(InvestFundFactory::create)
                .filter(investFund -> isNew(investFund) || isUpdated(investFund))
                .forEach(investFund -> addNewOrUpdateExisting(investFund));
    }

    private Boolean containsDataFileExtension(String record) {
        return record.matches("(.*)" + RATINGS_DATA_FILE_EXTENSION + "(.*)");
    }

    private boolean isNew(InvestFund investFund) {
        return Objects.isNull(repository.getById(investFund.getId()));
    }

    private boolean isUpdated(InvestFund investFund) {
        InvestFund retrievedInvestFund = repository.getById(investFund.getId());
        return investFund.getLastRatingDate().isAfter(retrievedInvestFund.getLastRatingDate());
    }

    private void addNewOrUpdateExisting(InvestFund investFund) {
        if (isNew(investFund)) {
            repository.add(investFund);
        } else {
            InvestFund updatedInvestFund = repository.getById(investFund.getId());
            updatedInvestFund.setName(investFund.getName());
            updatedInvestFund.setLastRatingDate(investFund.getLastRatingDate());
        }
    }
}
