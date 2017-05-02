package com.isacademy.jjdd1.czterystrony.initializers;

import com.isacademy.jjdd1.czterystrony.utilities.RatingsDownloader;
import com.isacademy.jjdd1.czterystrony.utilities.RatingsUnzipper;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class DatabaseInitializer {

    @EJB
    private RatingsDownloader ratingsDownloader;

    @EJB
    private RatingsUnzipper ratingsUnzipper;

    @PostConstruct
    void initialize() {
        ratingsDownloader.download();
        ratingsUnzipper.unzip();
    }
}
