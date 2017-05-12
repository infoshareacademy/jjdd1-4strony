package com.isacademy.jjdd1.czterystrony.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.isacademy.jjdd1.czterystrony.queries.RatingNamedNativeQueries;
import com.isacademy.jjdd1.czterystrony.services.JsonDateSerializer;

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
@NamedQueries({
        @NamedQuery(
                name = "Rating.getByFundAndDate",
                query = "SELECT r FROM Rating r WHERE r.date = :date AND r.investFund = :investFund"
        ),
        @NamedQuery(
                name = "Rating.getByFundInTimeRange",
                query = "SELECT r FROM Rating r WHERE r.investFund = :investFund AND r.date >= :startDate AND r.date <= :endDate"
        ),
        @NamedQuery(
                name = "Rating.getOldestForFund",
                query = "SELECT r FROM Rating r WHERE r.investFund = :investFund ORDER BY r.date ASC"
        ),
        @NamedQuery(
                name = "Rating.getNewestForFund",
                query = "SELECT r FROM Rating r WHERE r.investFund = :investFund ORDER BY r.date DESC"
        ),
        @NamedQuery(
                name = "Rating.getAllByFund",
                query = "SELECT r FROM Rating r WHERE r.investFund = :investFund ORDER BY r.date ASC"
        )
})
@NamedNativeQuery(
        name = "Rating.insertDataFromCsv",
        query = RatingNamedNativeQueries.insertFromCsv
)
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonSerialize(using = JsonDateSerializer.class)
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

    @JsonIgnore
    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    @JsonIgnore
    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    @JsonIgnore
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

    @JsonIgnore
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
