package isacademy.jjdd1.czterystrony.webapp.persistance.dbviews;

import isacademy.jjdd1.czterystrony.webapp.persistance.model.InvestFundDetails;
import isacademy.jjdd1.czterystrony.webapp.persistance.model.PensionFundDetails;
import isacademy.jjdd1.czterystrony.webapp.persistance.repositories.InvestFundDetailsRepository;
import isacademy.jjdd1.czterystrony.webapp.persistance.repositories.PensionFundDetailsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Startup
@Singleton
@DependsOn({"DatabaseInitializer", "InvestFundsInitializer", "RatingsInitializer"})
public class Views {
    private List<InvestFundDetails> allFunds;
    private List<InvestFundDetails> promotedFunds;
    private List<InvestFundDetails> notPromotedFunds;
    private List<PensionFundDetails> allPensionFunds;

    private static Logger log = LoggerFactory.getLogger(Views.class);

    @Inject
    InvestFundDetailsRepository repository;

    @Inject
    PensionFundDetailsRepository pensionFundDetailsRepository;

    @PostConstruct
    @Asynchronous
    public void updateViews() {
        this.allFunds = repository.getAll().stream()
                .sorted(Comparator.comparing(InvestFundDetails::getName))
                .collect(Collectors.toList());

        this.promotedFunds = allFunds.stream()
                .filter(f -> f.getPriority() > 0)
                .sorted(Comparator.comparing(InvestFundDetails::getPriority).reversed())
                .collect(Collectors.toList());

        this.notPromotedFunds = allFunds.stream()
                .filter(f -> f.getPriority() == 0)
                .collect(Collectors.toList());

        this.allPensionFunds = pensionFundDetailsRepository.getAll().stream()
                .sorted(Comparator.comparing(PensionFundDetails::getName))
                .collect(Collectors.toList());

        log.info("Lists updated.");
    }

    public List<InvestFundDetails> getAllFunds() {
        return allFunds;
    }

    public List<InvestFundDetails> getPromotedFunds() {
        return promotedFunds;
    }

    public List<InvestFundDetails> getNotPromotedFunds() {
        return notPromotedFunds;
    }

    public List<PensionFundDetails> getAllPensionFunds() {
        return allPensionFunds;
    }
}
