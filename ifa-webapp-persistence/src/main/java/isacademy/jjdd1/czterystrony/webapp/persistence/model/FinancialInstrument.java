package isacademy.jjdd1.czterystrony.webapp.persistence.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.isacademy.jjdd1.czterystrony.serializers.JsonDateSerializer;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@MappedSuperclass
public abstract class FinancialInstrument {

    @Id
    @NotNull
    String id;

    @NotNull
    String name;

    @JsonSerialize(using = JsonDateSerializer.class)
    @NotNull
    LocalDate lastRatingDate;

    protected FinancialInstrument() {
    }

    protected static abstract class Builder<T extends FinancialInstrument, B extends Builder<T, B>> {

        T instrument;
        B builder;

        protected Builder() {
            instrument = createInstrument();
            builder = createBuilder();
        }

        protected abstract T createInstrument();
        protected abstract B createBuilder();

        public B withId(String id) {
            instrument.id = id;
            return builder;
        }

        public B withName(String name) {
            instrument.name = name;
            return builder;
        }

        public B withLastRatingDate(LocalDate lastRatingDate) {
            instrument.lastRatingDate = lastRatingDate;
            return builder;
        }

        public T build() {
            return instrument;
        }
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLastRatingDate(LocalDate lastRatingDate) {
        this.lastRatingDate = lastRatingDate;
    }

    public LocalDate getLastRatingDate() {
        return lastRatingDate;
    }
}
