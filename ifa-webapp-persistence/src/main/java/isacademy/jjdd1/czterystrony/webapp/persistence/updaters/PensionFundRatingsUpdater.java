package isacademy.jjdd1.czterystrony.webapp.persistence.updaters;

import isacademy.jjdd1.czterystrony.webapp.persistence.factories.PensionFundRatingFactory;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.PensionFund;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.PensionFundRatingRepository;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.PensionFundRepository;
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
public class PensionFundRatingsUpdater {

    private static Logger log = LoggerFactory.getLogger(PensionFundRatingsUpdater.class);

    @Inject
    PensionFundRepository pensionFundRepository;

    @Inject
    PensionFundRatingRepository pensionFundRatingRepository;

    public void update() throws IOException {
        List<PensionFund> pensionFunds = pensionFundRepository.getAll();

        for (PensionFund pensionFund : pensionFunds) {
            updateRatingsFor(pensionFund);
        }
    }

    private void updateRatingsFor(PensionFund pensionFund) throws IOException {
        Path filePath = TMP_PROJECT_FOLDER.resolve(pensionFund.getId() + RATINGS_DATA_FILE_EXTENSION);
        InputStream stream = Files.newInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        bufferedReader.lines()
                .skip(RECORDS_TO_SKIP_IN_RATINGS_FILE)
                .map(record -> PensionFundRatingFactory.create(record, pensionFund))
                .filter(ofeRating -> ofeRating.getDate().isAfter(pensionFund.getLastRatingDate()))
                .filter(ofeRating -> pensionFundRatingRepository.getByFundAndDate(pensionFund.getId(), ofeRating.getDate()).isEmpty())
                .peek(ofeRating -> log.info("New rating for: {} with date: {}", pensionFund.getId(), ofeRating.getDate()))
                .forEach(ofeRating -> pensionFundRatingRepository.add(ofeRating, pensionFund.getId()));
    }
}
