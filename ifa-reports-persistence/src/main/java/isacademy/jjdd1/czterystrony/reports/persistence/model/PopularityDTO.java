package isacademy.jjdd1.czterystrony.reports.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PopularityDTO {

    @JsonProperty("id")
    private String instrumentId;

    @JsonProperty("name")
    private String instrumentName;

    @JsonProperty("clicks")
    private int clicks;

    public PopularityDTO() {
    }

    public PopularityDTO(String instrumentId, String instrumentName, int clicks) {
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

    public int getClicks() {
        return clicks;
    }
}
