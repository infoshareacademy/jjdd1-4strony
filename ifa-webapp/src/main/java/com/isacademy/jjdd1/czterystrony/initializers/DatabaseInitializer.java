package com.isacademy.jjdd1.czterystrony.initializers;

import com.isacademy.jjdd1.czterystrony.utilities.PensionFundDownloader;
import com.isacademy.jjdd1.czterystrony.utilities.PensionFundUnzipper;
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
    PensionFundDownloader pensionFundDownloader;

    @Inject
    RatingsUnzipper ratingsUnzipper;

    @Inject
    PensionFundUnzipper pensionFundUnzipper;

    @PostConstruct
    void initialize() {
        ratingsDownloader.download();
        pensionFundDownloader.download();
        ratingsUnzipper.unzip();
        pensionFundUnzipper.unzip();
    }
}
