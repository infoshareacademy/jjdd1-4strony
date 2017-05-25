package isacademy.jjdd1.czterystrony.webapp.persistence.initializers;

import com.isacademy.jjdd1.czterystrony.utilities.RatingsDownloader;
import com.isacademy.jjdd1.czterystrony.utilities.RatingsUnzipper;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class DatabaseInitializer {

    @Inject
    RatingsDownloader ratingsDownloader;

    @Inject
    RatingsUnzipper ratingsUnzipper;

    @PostConstruct
    void initialize() {
        ratingsDownloader.download();
        ratingsUnzipper.unzip();
    }
}
