package isacademy.jjdd1.czterystrony.reports.persistance.model;

public class InvestFundPopularity extends Popularity {
    public InvestFundPopularity(String instrumentId, String instrumentName, int clicks) {
        super(instrumentId, instrumentName, clicks);
        instrument = Instrument.INVEST_FUND;
    }
}
