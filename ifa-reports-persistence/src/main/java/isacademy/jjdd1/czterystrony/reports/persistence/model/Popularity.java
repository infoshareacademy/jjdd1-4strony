package isacademy.jjdd1.czterystrony.reports.persistence.model;

public abstract class Popularity {

    protected String instrumentId;
    protected String instrumentName;
    protected Instrument instrument;
    protected int clicks;

    protected Popularity(String instrumentId, String instrumentName, int clicks) {
        this.instrumentId = instrumentId;
        this.instrumentName = instrumentName;
        this.clicks = clicks;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public String getInstrumentName() {
        return instrumentName;
    }


    public Instrument getInstrument() {
        return instrument;
    }

    public int getClicks() {
        return clicks;
    }
}