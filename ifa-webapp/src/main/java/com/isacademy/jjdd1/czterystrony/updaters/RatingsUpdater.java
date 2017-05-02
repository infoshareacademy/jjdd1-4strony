package com.isacademy.jjdd1.czterystrony.updaters;

import com.isacademy.jjdd1.czterystrony.factories.RatingFactory;
import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;
import com.isacademy.jjdd1.czterystrony.repositories.RatingRepository;
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

import static com.isacademy.jjdd1.czterystrony.utilities.Constants.*;

@Stateless
public class RatingsUpdater {

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
                .map(record -> RatingFactory.create(record, investFund))
                .filter(rating -> rating.getDate().isAfter(investFund.getLastRatingDate()))
                .filter(rating -> ratingRepository.getByFundAndDate(investFund, rating.getDate()).isEmpty())
                .peek(rating -> log.info("New rating for: {} with date: {}", investFund.getId(), rating.getDate()))
                .forEach(rating -> ratingRepository.add(rating, investFund.getId()));
    }
}