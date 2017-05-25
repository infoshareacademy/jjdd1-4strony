package isacademy.jjdd1.czterystrony.webapp.persistence.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.isacademy.jjdd1.czterystrony.serializers.JsonDateSerializer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvestFundDetails {

    private String name;

    private String id;

    private int priority;

    @JsonSerialize(using = JsonDateSerializer.class)
    private LocalDate date;

    private BigDecimal close;

    private BigDecimal diff;

    public InvestFundDetails(String name,
                             String id,
                             int priority,
                             LocalDate date,
                             BigDecimal close,
                             BigDecimal diff) {
        this.name = name;
        this.id = id;
        this.priority = priority;
        this.date = date;
        this.close = close;
        this.diff = diff;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getClose() {
        return close;
    }

    public BigDecimal getDiff() {
        return diff;
    }
}
