package com.isacademy.jjdd1.czterystrony.services;

import com.isacademy.jjdd1.czterystrony.repository.InvestFundRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class DatabaseInitializer {

    @EJB
    private InvestFundRepository investFundRepository;

    @EJB
    private InvestFundInitializer investFundInitializer;

    @EJB
    private RatingInitializer ratingInitializer;

    @EJB
    private RatingsDownloader ratingsDownloader;

    @EJB
    private RatingsUnzipper ratingsUnzipper;

    @PostConstruct
    void initialize() {
        if (investFundRepository.getAll().isEmpty()) {
            ratingsDownloader.download();
            ratingsUnzipper.unzip();
            investFundInitializer.initialize();
            ratingInitializer.initialize();
        }
    }
}
