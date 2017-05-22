package com.isacademy.jjdd1.czterystrony.initializers;


import com.isacademy.jjdd1.czterystrony.repositories.OfeRatingRepository;
import com.isacademy.jjdd1.czterystrony.repositories.OfeRepository;
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
@DependsOn({"DatabaseInitializer", "OfeInitializer"})
public class OfeRatingsInitializer {
    private static Logger log = LoggerFactory.getLogger(OfeRatingsInitializer.class);

    @Inject
    OfeRepository ofeRepository;

    @Inject
    OfeRatingRepository ofeRatingRepository;

    @PostConstruct
    public void initialize() {
        ofeRepository.getAll().stream()
                .filter(ofe -> ofe.getOfeRatings().isEmpty())
                .peek(ofe -> log.info("Initializing ofe ratings for: {}", ofe.getId()))
                .map(ofe -> TMP_PROJECT_FOLDER.resolve(ofe.getId() + RATINGS_DATA_FILE_EXTENSION))
                .forEach(path -> ofeRatingRepository.insertDataFromCsv(path.toString()));
    }
}
