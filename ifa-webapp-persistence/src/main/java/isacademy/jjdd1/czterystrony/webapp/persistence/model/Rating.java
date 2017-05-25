package isacademy.jjdd1.czterystrony.webapp.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.isacademy.jjdd1.czterystrony.serializers.JsonDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonSerialize(using = JsonDateSerializer.class)
    @NotNull
    LocalDate date;

    @Digits(integer = 6, fraction = 2)
    BigDecimal open;

    @Digits(integer = 6, fraction = 2)
    BigDecimal high;

    @Digits(integer = 6, fraction = 2)
    BigDecimal low;

    @Digits(integer = 6, fraction = 2)
    BigDecimal close;

    protected Rating() {
    }

    public static abstract class Builder<T extends Rating, B extends Builder<T, B>> {

        T rating;
        B builder;

        Builder() {
            rating = createRating();
            builder = createBuilder();
        }

        abstract T createRating();
        abstract B createBuilder();

        public B withDate(LocalDate date) {
            rating.date = date;
            return builder;
        }

        public B withOpen(BigDecimal open) {
            rating.open = open;
            return builder;
        }

        public B withHigh(BigDecimal high) {
            rating.high = high;
            return builder;
        }

        public B withLow(BigDecimal low) {
            rating.low = low;
            return builder;
        }

        public B withClose(BigDecimal close) {
            rating.close = close;
            return builder;
        }

        public abstract B withInstrument(FinancialInstrument instrument);

        public T build() {
            return rating;
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
}
