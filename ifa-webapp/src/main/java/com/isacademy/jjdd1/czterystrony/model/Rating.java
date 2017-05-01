package com.isacademy.jjdd1.czterystrony.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(indexes = {@Index(
        name = "UX_Rating_id_date_investFund_id",
        columnList = "id,date,investFund_id")
})
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

    @Digits(integer = 6, fraction = 2)
    private BigDecimal open;

    @Digits(integer = 6, fraction = 2)
    private BigDecimal high;

    @Digits(integer = 6, fraction = 2)
    private BigDecimal low;

    @Digits(integer = 6, fraction = 2)
    private BigDecimal close;

    @ManyToOne
    private InvestFund investFund;

    public Rating() {
    }

    public Rating(Builder builder) {
        this.date = builder.date;
        this.open = builder.open;
        this.high = builder.high;
        this.low = builder.low;
        this.close = builder.close;
        this.investFund = builder.investFund;
    }

    public static class Builder {
        private LocalDate date;
        private BigDecimal open;
        private BigDecimal high;
        private BigDecimal low;
        private BigDecimal close;
        private InvestFund investFund;

        public Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder withOpen(BigDecimal open) {
            this.open = open;
            return this;
        }

        public Builder withHigh(BigDecimal high) {
            this.high = high;
            return this;
        }

        public Builder withLow(BigDecimal low) {
            this.low = low;
            return this;
        }

        public Builder withClose(BigDecimal close) {
            this.close = close;
            return this;
        }

        public Builder withInvestFund(InvestFund investFund) {
            this.investFund = investFund;
            return this;
        }

        public Rating build() {
            return new Rating(this);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public InvestFund getInvestFund() {
        return investFund;
    }

    public void setInvestFund(InvestFund investFund) {
        this.investFund = investFund;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", date=" + date +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", investFund=" + investFund +
                '}';
    }
}