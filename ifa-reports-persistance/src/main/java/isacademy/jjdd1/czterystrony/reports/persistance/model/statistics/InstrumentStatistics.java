package isacademy.jjdd1.czterystrony.reports.persistance.model.statistics;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class InstrumentStatistics {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "INSTRUMENT_ID")
    String instrumentId;

    @Column(name = "INSTRUMENT_NAME")
    String instrumentName;

    @Column(name = "DATE_TIME")
    LocalDateTime dateTime;

    @Column(name = "USER")
    String user;

    protected InstrumentStatistics() {
    }

    protected static abstract class Builder<T extends InstrumentStatistics, B extends Builder<T, B>> {

        T instrument;
        B builder;

        protected Builder() {
            instrument = createInstrument();
            builder = createBuilder();
        }

        protected abstract T createInstrument();
        protected abstract B createBuilder();

        public B withInstrumentId(String instrumentId) {
            instrument.instrumentId = instrumentId;
            return builder;
        }

        public B withInstrumentName(String instrumentName) {
            instrument.instrumentName = instrumentName;
            return builder;
        }

        public B withUser(String user) {
            instrument.user = user;
            return builder;
        }

        public T build() {
            return instrument;
        }
    }

    public Long getId() {
        return id;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getUser() {
        return user;
    }
}
