package isacademy.jjdd1.czterystrony.webapp.persistence.repositories;

import isacademy.jjdd1.czterystrony.webapp.persistence.model.PensionFund;

import javax.ejb.Stateless;

@Stateless
public class PensionFundRepository extends InstrumentRepository<PensionFund> {

    public PensionFundRepository() {
        super(PensionFund.class);
    }
}
