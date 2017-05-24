package isacademy.jjdd1.czterystrony.reports.persistance.model.statistics;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = {@Index(
        name = "UX_id_InvestFundId",
        columnList = "ID,INSTRUMENT_ID")
})
public class PensionFundStatistics extends InstrumentStatistics {
}
