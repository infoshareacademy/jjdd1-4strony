package com.isacademy.jjdd1.czterystrony.model;

import com.isacademy.jjdd1.czterystrony.queries.InvestFundNamedNativeQueries;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {@Index(
        name = "UX_InvestFund_id_name",
        columnList = "id,name")
})
@SqlResultSetMapping(name = "InvestFundDetailsMapping",
        classes = {
                @ConstructorResult(
                        targetClass = InvestFundDetails.class,
                        columns = {
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "id", type = String.class),
                                @ColumnResult(name = "priority", type = int.class),
                                @ColumnResult(name = "date", type = LocalDate.class),
                                @ColumnResult(name = "close", type = BigDecimal.class),
                                @ColumnResult(name = "diff", type = BigDecimal.class)
                        }
                )}
)
@NamedNativeQuery(
        name = "InvestFund.getAllWithDetails",
        query = InvestFundNamedNativeQueries.investFundDetails,
        resultSetMapping = "InvestFundDetailsMapping")
public class InvestFund {

    @Id
    @NotNull
    @Size(min = 6, max = 6)
    private String id;

    @NotNull
    private String name;

    @NotNull
    private LocalDate lastRatingDate;

    @Min(0)
    @Max(100)
    @NotNull
    private int priority;

    @OneToMany(mappedBy = "investFund", cascade = CascadeType.PERSIST)
    private List<Rating> ratings = new ArrayList<>();

    public InvestFund() {
    }

    public InvestFund(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.lastRatingDate = builder.lastRatingDate;
    }

    public static class Builder {
        private String id;
        private String name;
        private LocalDate lastRatingDate;

        public InvestFund.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public InvestFund.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public InvestFund.Builder withLastRatingDate(LocalDate lastRatingDate) {
            this.lastRatingDate = lastRatingDate;
            return this;
        }

        public InvestFund build() {
            return new InvestFund(this);
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

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return "InvestFund{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastRatingDate=" + lastRatingDate +
                ", priority=" + priority +
                '}';
    }
}
