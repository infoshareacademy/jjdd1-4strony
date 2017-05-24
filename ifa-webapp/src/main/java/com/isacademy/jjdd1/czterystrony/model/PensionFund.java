package com.isacademy.jjdd1.czterystrony.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.isacademy.jjdd1.czterystrony.queries.PensionFundNamedNativeQueries;
import com.isacademy.jjdd1.czterystrony.services.JsonDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {@Index(
        name = "UX_PensionFund_id_name",
        columnList = "id,name")
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "PensionFundDetailsMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = PensionFundDetails.class,
                                columns = {
                                        @ColumnResult(name = "name", type = String.class),
                                        @ColumnResult(name = "id", type = String.class),
                                        @ColumnResult(name = "date", type = LocalDate.class),
                                        @ColumnResult(name = "close", type = BigDecimal.class),
                                        @ColumnResult(name = "diff", type = BigDecimal.class)
                                })})
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "PensionFund.getAllWithDetails",
                query = PensionFundNamedNativeQueries.allWithDetails,
                resultSetMapping = "PensionFundDetailsMapping"),
        @NamedNativeQuery(
                name = "PensionFund.getByIdWithDetails",
                query = PensionFundNamedNativeQueries.byIdWithDetails,
                resultSetMapping = "PensionFundDetailsMapping")
        })

public class PensionFund {

    @Id
    @NotNull
    private String id;

    @NotNull
    private String name;

    @JsonSerialize(using = JsonDateSerializer.class)
    @NotNull
    private LocalDate lastRatingDate;

    @JsonIgnore
    @OneToMany(mappedBy = "pensionFund", cascade = CascadeType.PERSIST)
    private List<PensionFundRating> pensionFundRatings = new ArrayList<>();

    public PensionFund() {
    }

    public PensionFund(PensionFund.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.lastRatingDate = builder.lastRatingDate;
    }

    public static class Builder {
        private String id;
        private String name;
        private LocalDate lastRatingDate;

        public PensionFund.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public PensionFund.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public PensionFund.Builder withLastRatingDate(LocalDate lastRatingDate) {
            this.lastRatingDate = lastRatingDate;
            return this;
        }

        public PensionFund build() {
            return new PensionFund(this);
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

    public List<PensionFundRating> getPensionFundRatings() {
        return pensionFundRatings;
    }

    @Override
    public String toString() {
        return "PensionFund{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastRatingDate=" + lastRatingDate +
                ", ratings=" + pensionFundRatings +
                '}';
    }

}
