package isacademy.jjdd1.czterystrony.webapp.persistence.initializers;

import isacademy.jjdd1.czterystrony.webapp.persistence.model.FinancialInstrument;
import isacademy.jjdd1.czterystrony.webapp.persistence.repositories.InstrumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.isacademy.jjdd1.czterystrony.Constants.RATINGS_DATA_FILE_EXTENSION;
import static com.isacademy.jjdd1.czterystrony.Constants.TMP_PROJECT_FOLDER;

public abstract class RatingsInitializer {

    private static Logger log = LoggerFactory.getLogger(RatingsInitializer.class);

//    public void initialize(InstrumentRepository<FinancialInstrument> instrumentRepository, RatingRepository ratingRepository) {
//        instrumentRepository.getAll().stream()
//                .filter(fund -> fund.getRatings().isEmpty())
//                .peek(fund -> log.info("Initializing ratings for: {}", fund.getId()))
//                .map(fund -> TMP_PROJECT_FOLDER.resolve(fund.getId() + RATINGS_DATA_FILE_EXTENSION))
//                .forEach(path -> ratingRepository.insertDataFromCsv(path.toString()));
//    }
}
