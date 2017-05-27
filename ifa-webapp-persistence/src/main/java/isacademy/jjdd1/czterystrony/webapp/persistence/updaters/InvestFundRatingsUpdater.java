package isacademy.jjdd1.czterystrony.webapp.persistence.updaters;

import isacademy.jjdd1.czterystrony.webapp.persistence.factories.InvestFundRatingFactory;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFund;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRepository;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InvestFundRatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static com.isacademy.jjdd1.czterystrony.Constants.*;

@Stateless
public class InvestFundRatingsUpdater {

    private static Logger log = LoggerFactory.getLogger(InvestFundRepository.class);

    @Inject
    InvestFundRepository instrumentRepository;

    @Inject
    InvestFundRatingRepository ratingRepository;

    public void update() throws IOException {
        List<InvestFund> investFunds = instrumentRepository.getAll();

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
                .map(record -> InvestFundRatingFactory.create(record, investFund))
                .filter(rating -> rating.getDate().isAfter(investFund.getLastRatingDate()))
                .filter(rating -> ratingRepository.getByFundAndDate(investFund.getId(), rating.getDate()).isEmpty())
                .peek(rating -> log.info("New rating for: {} with date: {}", investFund.getId(), rating.getDate()))
                .forEach(rating -> ratingRepository.add(rating, investFund.getId()));
    }
}
