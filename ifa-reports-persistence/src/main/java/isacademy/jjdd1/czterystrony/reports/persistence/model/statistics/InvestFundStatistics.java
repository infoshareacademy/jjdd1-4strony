package isacademy.jjdd1.czterystrony.reports.persistence.model.statistics;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import isacademy.jjdd1.czterystrony.reports.persistence.model.InvestFundPopularity;
import isacademy.jjdd1.czterystrony.reports.persistence.queries.ReportQueries;

import javax.persistence.*;
import javax.persistence.NamedNativeQueries;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(indexes = {@Index(
        name = "UX_id_InvestFundId",
        columnList = "ID,INSTRUMENT_ID")
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "InvestFundPopularityMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = InvestFundPopularity.class,
                                columns = {
                                        @ColumnResult(name = "name", type = String.class),
                                        @ColumnResult(name = "id", type = String.class),
                                        @ColumnResult(name = "clicks", type = int.class),
                                }
                        )})
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "InvestFundPopularity.getAll",
                query = ReportQueries.getAllInvestFundPopularity,
                resultSetMapping = "InvestFundPopularityMapping"),
        @NamedNativeQuery(
                name = "InvestFundPopularity.getInTimeRange",
                query = ReportQueries.getAllInvestFundPopularityInTimeRange,
                resultSetMapping = "InvestFundPopularityMapping")
})
public class InvestFundStatistics extends InstrumentStatistics {

    @Column(name = "START_DATE")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

    @Column(name = "END_DATE")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    @Column(name = "ZIGZAG_VALUE")
    private int zigZag;

    @Column(name = "AVERAGE_DATE_DIFFERENCE")
    private int averageDateDifference;

    @Column(name = "AVERAGE_VALUE_DIFFERENCE")
    private BigDecimal averageValueDifference;

    public InvestFundStatistics() {
    }

    public static class Builder extends InstrumentStatistics.Builder<InvestFundStatistics, Builder> {

        @Override
        InvestFundStatistics createInstrumentStatistics() {
            return new InvestFundStatistics();
        }

        @Override
        Builder createBuilder() {
            return this;
        }

        public Builder withStartDate(LocalDate date) {
            instrumentStatistics.startDate = date;
            return builder;
        }

        public Builder withEndDate(LocalDate date) {
            instrumentStatistics.endDate = date;
            return builder;
        }

        public Builder withZigZag(int zigZag) {
            instrumentStatistics.zigZag = zigZag;
            return builder;
        }

        public Builder withAverageDateDifference(int averageDateDifference) {
            instrumentStatistics.averageDateDifference = averageDateDifference;
            return builder;
        }

        public Builder withAverageValueDifference(BigDecimal averageValueDifference) {
            instrumentStatistics.averageValueDifference = averageValueDifference;
            return builder;
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getZigZag() {
        return zigZag;
    }

    public int getAverageDateDifference() {
        return averageDateDifference;
    }

    public BigDecimal getAverageValueDifference() {
        return averageValueDifference;
    }

    @Override
    public String toString() {
        return "InvestFundStatistics{" +
                "id=" + id +
                ", instrumentId='" + instrumentId + '\'' +
                ", instrumentName='" + instrumentName + '\'' +
                ", dateTime=" + dateTime +
                ", user='" + user + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", zigZag=" + zigZag +
                ", averageDateDifference=" + averageDateDifference +
                ", averageValueDifference=" + averageValueDifference +
                '}';
    }
}
