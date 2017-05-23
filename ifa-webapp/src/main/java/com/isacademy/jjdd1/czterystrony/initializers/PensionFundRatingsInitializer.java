package com.isacademy.jjdd1.czterystrony.initializers;


import com.isacademy.jjdd1.czterystrony.repositories.PensionFundRatingRepository;
import com.isacademy.jjdd1.czterystrony.repositories.PensionFundRepository;
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
@DependsOn({"DatabaseInitializer", "PensionFundInitializer"})
public class PensionFundRatingsInitializer {
    private static Logger log = LoggerFactory.getLogger(PensionFundRatingsInitializer.class);

    @Inject
    PensionFundRepository pensionFundRepository;

    @Inject
    PensionFundRatingRepository pensionFundRatingRepository;

    @PostConstruct
    public void initialize() {
        pensionFundRepository.getAll().stream()
                .filter(ofe -> ofe.getPensionFundRatings().isEmpty())
                .peek(ofe -> log.info("Initializing pension funds ratings for: {}", ofe.getId()))
                .map(ofe -> TMP_PROJECT_FOLDER.resolve(ofe.getId() + RATINGS_DATA_FILE_EXTENSION))
                .forEach(path -> pensionFundRatingRepository.insertDataFromCsv(path.toString()));
    }
}
