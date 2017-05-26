package isacademy.jjdd1.czterystrony.webapp.persistence.repositories;

import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFund;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.time.LocalDate;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class InvestFundFacade {

    private static final Logger log = LoggerFactory.getLogger(InvestFundRepository.class);

    @Inject
    InvestFundRepository instrumentRepository;

    public void updateName(String id, String name) {
        getInvestFundById(id).setName(name);
        log.info("Updated name: {} for {} fund.", name, id);
    }

    public void updatePriority(String id, int priority) {
        getInvestFundById(id).setPriority(priority);
        log.info("Updated priority: {} for {} fund.", priority, id);
    }

    public void updateLastRatingDate(String id, LocalDate date) {
        getInvestFundById(id).setLastRatingDate(date);
        log.info("Updated last rating date: {} for {} fund.", date, id);
    }

    private InvestFund getInvestFundById(String id) {
        return instrumentRepository.getById(id);
    }
}
