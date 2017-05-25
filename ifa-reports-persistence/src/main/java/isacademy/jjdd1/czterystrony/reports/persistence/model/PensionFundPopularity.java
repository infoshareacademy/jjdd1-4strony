package isacademy.jjdd1.czterystrony.reports.persistence.model;

import com.isacademy.jjdd1.czterystrony.instruments.Instrument;

public class PensionFundPopularity extends Popularity {
    protected PensionFundPopularity(String instrumentId, String instrumentName, int clicks) {
        super(instrumentId, instrumentName, clicks);
        instrument = Instrument.PENSION_FUND;
    }
}
