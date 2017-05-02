package com.isacademy.jjdd1.czterystrony.dbviews;

import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.util.List;
import java.util.stream.Collectors;

@Startup
@Singleton
@DependsOn({"DatabaseInitializer", "InvestFundsInitializer", "RatingsInitializer"})
public class Views {
    private List<Object[]> allFunds;
    private List<Object[]> promotedFunds;
    private List<Object[]> notPromotedFunds;

    @EJB
    private InvestFundRepository investFundRepository;

    @PostConstruct
    public void updateViews() {
        this.allFunds = investFundRepository.getAllWithCurrentRating().stream()
                .sorted((a, b) -> {
                    String c = (String) a[0];
                    String d = (String) b[0];
                    return c.compareTo(d);
                })
                .collect(Collectors.toList());

        this.promotedFunds = allFunds.stream()
                .filter(o -> (int) o[2] > 0)
                .sorted((a, b) -> {
                    Integer c = (int) a[2];
                    Integer d = (int) b[2];
                    return d.compareTo(c);
                })
                .collect(Collectors.toList());

        this.notPromotedFunds = allFunds.stream()
                .filter(o -> (int) o[2] == 0)
                .collect(Collectors.toList());
    }

    public List<Object[]> getAllFunds() {
        return allFunds;
    }

    public List<Object[]> getPromotedFunds() {
        return promotedFunds;
    }

    public List<Object[]> getNotPromotedFunds() {
        return notPromotedFunds;
    }
}
