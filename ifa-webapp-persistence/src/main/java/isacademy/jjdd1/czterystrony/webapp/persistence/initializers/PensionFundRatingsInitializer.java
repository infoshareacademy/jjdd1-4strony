package isacademy.jjdd1.czterystrony.webapp.persistence.initializers;

import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.PensionFundRatingRepository;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.PensionFundRepository;
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
@DependsOn({"DatabaseInitializer", "PensionFundsInitializer"})
public class PensionFundRatingsInitializer {

    private static final Logger log = LoggerFactory.getLogger(PensionFundRatingsInitializer.class);

    @Inject
    PensionFundRepository pensionFundRepository;

    @Inject
    PensionFundRatingRepository pensionFundRatingRepository;

    @PostConstruct
    public void initialize() {
        pensionFundRepository.getAll().stream()
                .filter(fund -> fund.getRatings().isEmpty())
                .peek(fund -> log.info("Initializing pension fund ratings for: {}", fund.getId()))
                .map(fund -> TMP_PROJECT_FOLDER.resolve(fund.getId() + RATINGS_DATA_FILE_EXTENSION))
                .forEach(path -> pensionFundRatingRepository.insertDataFromCsv(path.toString()));
    }
}
