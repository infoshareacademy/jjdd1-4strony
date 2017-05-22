package com.isacademy.jjdd1.czterystrony.initializers;

import com.isacademy.jjdd1.czterystrony.utilities.OfeDownloader;
import com.isacademy.jjdd1.czterystrony.utilities.OfeUnzipper;
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
    OfeDownloader ofeDownloader;

    @Inject
    RatingsUnzipper ratingsUnzipper;

    @Inject
    OfeUnzipper ofeUnzipper;

    @PostConstruct
    void initialize() {
        ratingsDownloader.download();
        ofeDownloader.download();
        ratingsUnzipper.unzip();
        ofeUnzipper.unzip();
    }
}
