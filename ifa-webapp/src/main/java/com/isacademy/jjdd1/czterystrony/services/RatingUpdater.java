package com.isacademy.jjdd1.czterystrony.services;

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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.isacademy.jjdd1.czterystrony.util.Constants.*;

@Stateless
public class RatingUpdater {

    private static Logger log = LoggerFactory.getLogger(InvestFundRepository.class);

    @EJB
    private InvestFundRepository investFundRepository;

    @EJB
    private RatingRepository ratingRepository;

    public void update() throws IOException {
        List<InvestFund> investFunds = investFundRepository.getAll();

        for (InvestFund investFund : investFunds) {
            updateRatingsFor(investFund);
        }
    }

    private void updateRatingsFor(InvestFund investFund) throws IOException {
        Path filePath = TMP_PROJECT_FOLDER.resolve(investFund.getId() + RATINGS_DATA_FILE_EXTENSION);
        InputStream stream = Files.newInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        bufferedReader.lines()
                .skip(RECORDS_TO_SKIP_IN_RATINGS_FILE)
                .map(record -> RatingFactory.create(record))
                .filter(rating -> rating.getDate().isAfter(investFund.getLastRatingDate()))
                .peek(rating -> log.info("New rating for: {} with date: {}", rating.getInvestFund().getId(), rating.getDate()))
                .forEach(rating -> ratingRepository.add(rating, investFund.getId()));
    }
}
