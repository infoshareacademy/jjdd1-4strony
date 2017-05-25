package isacademy.jjdd1.czterystrony.reports.persistence.model;

import com.isacademy.jjdd1.czterystrony.instruments.Instrument;

public class InvestFundPopularity extends Popularity {
    public InvestFundPopularity(String instrumentId, String instrumentName, int clicks) {
        super(instrumentId, instrumentName, clicks);
        instrument = Instrument.INVEST_FUND;
    }
}
