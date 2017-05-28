package isacademy.jjdd1.czterystrony.reports.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class InvestFundZigzagReport {

    @JsonProperty("id")
    private String instrumentId;

    @JsonProperty("name")
    private String instrumentName;

    @JsonProperty("zigzagValue")
    private int zigzagValue;

    @JsonProperty("averageDayDifference")
    private int averageDayDifference;

    @JsonProperty("averageValueDifference")
    private BigDecimal averageValueDifference;

    public InvestFundZigzagReport() {
    }

    public InvestFundZigzagReport(String instrumentName,
                                  String instrumentId,
                                  int zigzagValue,
                                  int averageDayDifference,
                                  BigDecimal averageValueDifference) {

        this.instrumentName = instrumentName;
        this.instrumentId = instrumentId;
        this.zigzagValue = zigzagValue;
        this.averageDayDifference = averageDayDifference;
        this.averageValueDifference = averageValueDifference;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public int getZigzagValue() {
        return zigzagValue;
    }

    public int getAverageDayDifference() {
        return averageDayDifference;
    }

    public BigDecimal getAverageValueDifference() {
        return averageValueDifference;
    }
}
