package com.isacademy.jjdd1.czterystrony.dbviews;

import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.util.List;

@Startup
@Singleton
@DependsOn({"DatabaseInitializer", "InvestFundsInitializer", "RatingsInitializer"})
public class Views {
    private List<Object[]> homePageTable;

    @EJB
    private InvestFundRepository investFundRepository;

    @PostConstruct
    public void updateViews() {
        this.homePageTable = investFundRepository.getAllWithCurrentRating();
    }

    public List<Object[]> getHomePageTable() {
        return homePageTable;
    }
}
