package isacademy.jjdd1.czterystrony.webapp.persistance.initializers;

import isacademy.jjdd1.czterystrony.webapp.persistance.repositories.PensionFundRatingRepository;
import isacademy.jjdd1.czterystrony.webapp.persistance.repositories.PensionFundRepository;
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
                .filter(pensionFund -> pensionFund.getPensionFundRatings().isEmpty())
                .peek(pensionFund -> log.info("Initializing pension funds ratings for: {}", pensionFund.getId()))
                .map(pensionFund -> TMP_PROJECT_FOLDER.resolve(pensionFund.getId() + RATINGS_DATA_FILE_EXTENSION))
                .forEach(path -> pensionFundRatingRepository.insertDataFromCsv(path.toString()));
    }
}
