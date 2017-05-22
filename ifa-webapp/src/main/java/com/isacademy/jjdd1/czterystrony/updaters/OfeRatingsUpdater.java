package com.isacademy.jjdd1.czterystrony.updaters;

import com.isacademy.jjdd1.czterystrony.factories.OfeRatingFactory;
import com.isacademy.jjdd1.czterystrony.factories.RatingFactory;
import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.model.Ofe;
import com.isacademy.jjdd1.czterystrony.repositories.OfeRatingRepository;
import com.isacademy.jjdd1.czterystrony.repositories.OfeRepository;
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

import static com.isacademy.jjdd1.czterystrony.utilities.Constants.*;

@Stateless
public class OfeRatingsUpdater {

    private static Logger log = LoggerFactory.getLogger(OfeRatingsUpdater.class);

    @Inject
    OfeRepository ofeRepository;

    @Inject
    OfeRatingRepository ofeRatingRepository;

    public void update() throws IOException {
        List<Ofe> ofes = ofeRepository.getAll();

        for (Ofe ofe : ofes) {
            updateRatingsFor(ofe);
        }
    }

    private void updateRatingsFor(Ofe ofe) throws IOException {
        Path filePath = TMP_PROJECT_FOLDER.resolve(ofe.getId() + RATINGS_DATA_FILE_EXTENSION);
        InputStream stream = Files.newInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        bufferedReader.lines()
                .skip(RECORDS_TO_SKIP_IN_RATINGS_FILE)
                .map(record -> OfeRatingFactory.create(record, ofe))
                .filter(ofeRating -> ofeRating.getDate().isAfter(ofe.getLastRatingDate()))
                .filter(ofeRating -> ofeRatingRepository.getByOfeAndDate(ofe, ofeRating.getDate()).isEmpty())
                .peek(ofeRating -> log.info("New rating for: {} with date: {}", ofe.getId(), ofeRating.getDate()))
                .forEach(ofeRating -> ofeRatingRepository.add(ofeRating, ofe.getId()));
    }
}
