package isacademy.jjdd1.czterystrony.webapp.persistence.repositories;

import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFund;

import javax.ejb.Stateless;

@Stateless
public class InvestFundRepository extends InstrumentRepository<InvestFund> {

    public InvestFundRepository() {
        super(InvestFund.class);
    }
}