package isacademy.jjdd1.czterystrony.webapp.persistance.initializers;

import isacademy.jjdd1.czterystrony.webapp.persistance.repositories.InvestFundRepository;
import isacademy.jjdd1.czterystrony.webapp.persistance.repositories.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import static com.isacademy.jjdd1.czterystrony.Constants.RATINGS_DATA_FILE_EXTENSION;
import static com.isacademy.jjdd1.czterystrony.Constants.TMP_PROJECT_FOLDER;

@Startup
@Singleton
@DependsOn({"DatabaseInitializer", "InvestFundsInitializer"})
public class RatingsInitializer {

    private static Logger log = LoggerFactory.getLogger(RatingsInitializer.class);

    @Inject
    InvestFundRepository investFundRepository;

    @Inject
    RatingRepository ratingRepository;

    @PostConstruct
    public void initialize() {
        investFundRepository.getAll().stream()
                .filter(fund -> fund.getRatings().isEmpty())
                .peek(fund -> log.info("Initializing ratings for: {}", fund.getId()))
                .map(fund -> TMP_PROJECT_FOLDER.resolve(fund.getId() + RATINGS_DATA_FILE_EXTENSION))
                .forEach(path -> ratingRepository.insertDataFromCsv(path.toString()));
    }
}