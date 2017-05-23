package com.isacademy.jjdd1.czterystrony.reports.model.statistics;

import com.isacademy.jjdd1.czterystrony.reports.model.report.InvestFundPopularity;
import com.isacademy.jjdd1.czterystrony.reports.queries.ReportQueries;

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
                resultSetMapping = "InvestFundPopularityMapping")
})
public class InvestFundStatistics extends InstrumentStatistics {

//    @Column(name = "START_DATE")
    private LocalDate startDate;

//    @Column(name = "END_DATE")
    private LocalDate endDate;

//    @Column(name = "ZIGZAG_VALUE")
    private int zigZag;

//    @Column(name = "AVERAGE_DATE_DIFFERENCE")
    private int averageDateDifference;

//    @Column(name = "AVERAGE_VALUE_DIFFERENCE")
    private BigDecimal averageValueDifference;

    public InvestFundStatistics() {
    }

    public static class Builder extends InstrumentStatistics.Builder<InvestFundStatistics, Builder> {

        @Override
        protected InvestFundStatistics createInstrument() {
            return new InvestFundStatistics();
        }

        @Override
        protected Builder createBuilder() {
            return this;
        }

        public Builder withStartDate(LocalDate date) {
            instrument.startDate = date;
            return builder;
        }

        public Builder withEndDate(LocalDate date) {
            instrument.endDate = date;
            return builder;
        }

        public Builder withZigZag(int zigZag) {
            instrument.zigZag = zigZag;
            return builder;
        }

        public Builder withAverageDateDifference(int averageDateDifference) {
            instrument.averageDateDifference = averageDateDifference;
            return builder;
        }

        public Builder withAverageValueDifference(BigDecimal averageValueDifference) {
            instrument.averageValueDifference = averageValueDifference;
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
