package isacademy.jjdd1.czterystrony.reports.persistence.model.statistics;

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

        T instrumentStatistics;
        B builder;

        protected Builder() {
            instrumentStatistics = createInstrumentStatistics();
            builder = createBuilder();
        }

        protected abstract T createInstrumentStatistics();
        protected abstract B createBuilder();

        public B withInstrumentId(String instrumentId) {
            instrumentStatistics.instrumentId = instrumentId;
            return builder;
        }

        public B withInstrumentName(String instrumentName) {
            instrumentStatistics.instrumentName = instrumentName;
            return builder;
        }

        public B withUser(String user) {
            instrumentStatistics.user = user;
            return builder;
        }

        public T build() {
            return instrumentStatistics;
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
