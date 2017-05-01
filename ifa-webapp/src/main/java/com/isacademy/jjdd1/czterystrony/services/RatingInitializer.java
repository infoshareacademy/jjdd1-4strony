package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.repository.InvestFundRepository;
import com.isacademy.jjdd1.czterystrony.repository.RatingRepository;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import static com.isacademy.jjdd1.czterystrony.util.Constants.RATINGS_DATA_FILE_EXTENSION;
import static com.isacademy.jjdd1.czterystrony.util.Constants.TMP_PROJECT_FOLDER;

@Startup
@Singleton
public class RatingInitializer {

    @EJB
    private InvestFundRepository investFundRepository;

    @EJB
    private RatingRepository ratingRepository;

    public void initialize() {
        investFundRepository.getAll().stream()
                .filter(fund -> fund.getRatings().isEmpty())
                .map(fund -> TMP_PROJECT_FOLDER.resolve(fund.getId() + RATINGS_DATA_FILE_EXTENSION))
                .forEach(path -> ratingRepository.insertDataFromCsv(path.toString()));
    }
}
