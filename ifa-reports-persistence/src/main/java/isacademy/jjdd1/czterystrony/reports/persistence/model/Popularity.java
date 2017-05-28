package isacademy.jjdd1.czterystrony.reports.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isacademy.jjdd1.czterystrony.instruments.Instrument;

public class Popularity {

    @JsonProperty("id")
    private String instrumentId;

    @JsonProperty("name")
    private String instrumentName;

    @JsonProperty("type")
    Instrument instrument;

    @JsonProperty("clicks")
    private int clicks;

    public Popularity() {
    }

    public Popularity(String instrumentId, String instrumentName, int clicks) {
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