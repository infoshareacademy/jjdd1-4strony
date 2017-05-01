package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.factories.InvestFundFactory;
import com.isacademy.jjdd1.czterystrony.factories.RatingFactory;
import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.repository.InvestFundRepository;
import com.isacademy.jjdd1.czterystrony.repository.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.isacademy.jjdd1.czterystrony.util.Constants.*;

@Stateless
public class DatabaseUpdater {

    private static Logger log = LoggerFactory.getLogger(InvestFundRepository.class);

    @EJB
    private InvestFundRepository investFundRepository;

    @EJB
    private RatingRepository ratingRepository;

    private List<InvestFund> investFunds;

    public void updateInvestFunds() throws IOException {
        URL url = new URL(FUNDS_LIST_SOURCE);
        URLConnection urlConnection = url.openConnection();
        InputStream stream = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        bufferedReader.lines()
                .filter(record -> containsDataFileExtension(record))
                .map(InvestFundFactory::create)
                .forEach(investFund -> addNewAndUpdateExistingFund(investFund));
    }

    private Boolean containsDataFileExtension(String record) {
        return record.matches("(.*)" + RATINGS_DATA_FILE_EXTENSION + "(.*)");
    }

    private void addNewAndUpdateExistingFund(InvestFund investFund) {
        InvestFund retrievedInvestFund = investFundRepository.queryById(investFund.getId());

        if (retrievedInvestFund == null) {
            investFundRepository.add(investFund);
        } else if (investFund.getLastRatingDate().isAfter(retrievedInvestFund.getLastRatingDate())) {
            investFundRepository.update(investFund);
        }
    }

    public void updateAllRatings() throws IOException {
        investFunds = investFundRepository.getAll();

        for (InvestFund investFund : investFunds) {
            String id = investFund.getId();
            Path path = TMP_PROJECT_FOLDER.resolve(id + RATINGS_DATA_FILE_EXTENSION);
            updateFundRatingsFromFile(path, investFund);
            log.info("Updated ratings for: {}", investFund.getId());

//        investFund = investFunds.get(5);
//        path = TMP_PROJECT_FOLDER.resolve(
//                investFund.getId() + RATINGS_DATA_FILE_EXTENSION);
//        updateFundRatingsFromFile(investFund, path);
        }
    }

    private void updateFundRatingsFromFile(Path path, InvestFund investFund) throws IOException {
        InputStream stream = Files.newInputStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        bufferedReader.lines()
                .skip(RECORDS_TO_SKIP_IN_RATINGS_FILE)
                .map(record -> RatingFactory.create(record))
                .filter(rating -> rating.getDate().isAfter(investFund.getLastRatingDate()))
                .peek(rating -> log.info("New rating for: {} with date: {}", rating.getInvestFund().getId(), rating.getDate()))
                .forEach(rating -> ratingRepository.add(rating, investFund.getId()));
    }

    private void appendRatingToDatabaseByInsertInto(String record) {

    }
}
