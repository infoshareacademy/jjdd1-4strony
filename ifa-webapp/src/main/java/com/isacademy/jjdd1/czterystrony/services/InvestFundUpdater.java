package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.factories.InvestFundFactory;
import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.repository.InvestFundRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static com.isacademy.jjdd1.czterystrony.util.Constants.*;

@Stateless
public class InvestFundUpdater {

    @EJB
    private InvestFundRepository investFundRepository;

    public void update() throws IOException {
        URL url = new URL(FUNDS_LIST_SOURCE);
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
        return investFundRepository.queryById(investFund.getId()) == null;
    }

    private boolean isUpdated(InvestFund investFund) {
        InvestFund retrievedInvestFund = investFundRepository.queryById(investFund.getId());
        return investFund.getLastRatingDate().isAfter(retrievedInvestFund.getLastRatingDate());
    }

    private void addNewOrUpdateExisting(InvestFund investFund) {
        if (isNew(investFund)) {
            investFundRepository.add(investFund);
        } else {
            investFundRepository.update(investFund);
        }
    }
}
