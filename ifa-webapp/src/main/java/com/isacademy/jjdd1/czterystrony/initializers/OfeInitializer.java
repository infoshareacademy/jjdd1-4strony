package com.isacademy.jjdd1.czterystrony.initializers;

import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;
import com.isacademy.jjdd1.czterystrony.repositories.OfeRepository;
import com.isacademy.jjdd1.czterystrony.repositories.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import static com.isacademy.jjdd1.czterystrony.utilities.Constants.RATINGS_DATA_FILE_EXTENSION;
import static com.isacademy.jjdd1.czterystrony.utilities.Constants.TMP_PROJECT_FOLDER;

@Startup
@Singleton
@DependsOn("DatabaseInitializer")
public class OfeInitializer {

    private static Logger log = LoggerFactory.getLogger(OfeInitializer.class);

    @Inject
    OfeRepository ofeRepository;

    @Inject
    RatingRepository ratingRepository;

    @PostConstruct
    public void initialize() {
        ofeRepository.getAll().stream()
                .filter(ofe -> ofe.getOfeRatings().isEmpty())
                .peek(ofe -> log.info("Initializing ratings for: {}", ofe.getId()))
                .map(ofe -> TMP_PROJECT_FOLDER.resolve(ofe.getId() + RATINGS_DATA_FILE_EXTENSION))
                .forEach(path -> ratingRepository.insertDataFromCsv(path.toString()));
    }
}
