package com.isacademy.jjdd1.czterystrony.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(indexes = {@Index(
        name = "UX_id_investFund_id",
        columnList = "id,investFund_id")
})
public class InvestFundStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private InvestFund investFund;

    private LocalDateTime dateTime;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private int zigZag;

    private BigDecimal averageDateDifference;

    private BigDecimal averageValueDifference;

    private String user;

    public InvestFundStatistics() {
    }

    public InvestFundStatistics(Builder builder) {
        this.dateTime = LocalDateTime.now();
        this.investFund = builder.investFund;
        this.zigZag= builder.zigZag;
        this.averageDateDifference = builder.averageDateDifference;
        this.averageValueDifference = builder.averageValueDifference;
        this.dateFrom = builder.dateFrom;
        this.dateTo = builder.dateTo;
        this.user = builder.user;
    }

    public static class Builder {
        private InvestFund investFund;
        private LocalDate dateFrom;
        private LocalDate dateTo;
        private int zigZag;
        private BigDecimal averageDateDifference;
        private BigDecimal averageValueDifference;
        private String user;

        public Builder withInvestFund(InvestFund investFund) {
            this.investFund = investFund;
            return this;
        }

        public Builder withDateFrom(LocalDate dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        public Builder withDateTo(LocalDate dateTo) {
            this.dateTo = dateTo;
            return this;
        }

        public Builder withZigZag(int zigZag) {
            this.zigZag = zigZag;
            return this;
        }

        public Builder withAverageDateDifference(BigDecimal averageDateDifference) {
            this.averageDateDifference = averageDateDifference;
            return this;
        }

        public Builder withAverageValueDifference(BigDecimal averageValueDifference) {
            this.averageValueDifference = averageValueDifference;
            return this;
        }

        public Builder withUser(String user) {
            this.user = user;
            return this;
        }

        public InvestFundStatistics build() {
            return new InvestFundStatistics(this);
        }
    }

    public BigDecimal getAverageDateDifference() {
        return averageDateDifference;
    }

    public BigDecimal getAverageValueDifference() {
        return averageValueDifference;
    }

    public int getZigZag() {
        return zigZag;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public Long getId() {
        return id;
    }

    public InvestFund getInvestFund() {
        return investFund;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "InvestFundStatistics{" +
                "id=" + id +
                ", fund='" + investFund + '\'' +
                ", date=" + dateTime +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", zigZag=" + zigZag +
                ", averageDateDifference=" + averageDateDifference +
                ", averageValueDifference=" + averageValueDifference +
                ", user='" + user + '\'' +
                '}';
    }
}
