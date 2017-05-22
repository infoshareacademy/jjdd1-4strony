package com.isacademy.jjdd1.czterystrony.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.isacademy.jjdd1.czterystrony.queries.OfeRatingNamedNativeQueries;
import com.isacademy.jjdd1.czterystrony.queries.RatingNamedNativeQueries;
import com.isacademy.jjdd1.czterystrony.services.JsonDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(indexes = {@Index(
        name = "UX_Rating_id_date_ofe_id",
        columnList = "id,date,ofe_id")
})
@NamedQueries({
        @NamedQuery(
                name = "OfeRating.getByOfeAndDate",
                query = "SELECT r FROM OfeRating r WHERE r.date = :date AND r.ofe = :ofe"
        ),
        @NamedQuery(
                name = "OfeRating.getAllByOfe",
                query = "SELECT r FROM OfeRating r WHERE r.ofe = :ofe ORDER BY r.date ASC"
        )
})
@NamedNativeQuery(
        name = "OfeRating.insertDataFromCsv",
        query = OfeRatingNamedNativeQueries.insertFromCsv
)
public class OfeRating {

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
    private Ofe ofe;

    public OfeRating() {
    }

    public OfeRating(Builder builder) {
        this.date = builder.date;
        this.open = builder.open;
        this.high = builder.high;
        this.low = builder.low;
        this.close = builder.close;
        this.ofe = builder.ofe;
    }

    public static class Builder {
        private LocalDate date;
        private BigDecimal open;
        private BigDecimal high;
        private BigDecimal low;
        private BigDecimal close;
        private Ofe ofe;

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

        public Builder withOfe(Ofe ofe) {
            this.ofe = ofe;
            return this;
        }

        public OfeRating build() {
            return new OfeRating(this);
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
    public Ofe getOfe() {
        return ofe;
    }

    public void setOfe(Ofe ofe) {
        this.ofe = ofe;
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
                ", ofe=" + ofe +
                '}';
    }
}
