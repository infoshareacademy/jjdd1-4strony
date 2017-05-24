package isacademy.jjdd1.czterystrony.reports.persistance.model;

public class PensionFundPopularity extends Popularity {
    protected PensionFundPopularity(String instrumentId, String instrumentName, int clicks) {
        super(instrumentId, instrumentName, clicks);
        instrument = Instrument.PENSION_FUND;
    }
}
