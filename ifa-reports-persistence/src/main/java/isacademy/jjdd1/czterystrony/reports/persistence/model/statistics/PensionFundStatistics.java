package isacademy.jjdd1.czterystrony.reports.persistence.model.statistics;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = {@Index(
        name = "UX_id_InvestFundId",
        columnList = "ID,INSTRUMENT_ID")
})
public class PensionFundStatistics extends InstrumentStatistics {
    public static class Builder extends InstrumentStatistics.Builder<PensionFundStatistics, Builder> {

        @Override
        PensionFundStatistics createInstrumentStatistics() {
            return null;
        }

        @Override
        Builder createBuilder() {
            return null;
        }
    }

    @Override
    public String toString() {
        return "PensionFundStatistics{" +
                "id=" + id +
                ", instrumentId='" + instrumentId + '\'' +
                ", instrumentName='" + instrumentName + '\'' +
                ", dateTime=" + dateTime +
                ", user='" + user + '\'' +
                '}';
    }
}
