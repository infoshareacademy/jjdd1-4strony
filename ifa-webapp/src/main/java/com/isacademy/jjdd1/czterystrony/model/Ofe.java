package com.isacademy.jjdd1.czterystrony.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.isacademy.jjdd1.czterystrony.queries.InvestFundNamedNativeQueries;
import com.isacademy.jjdd1.czterystrony.services.JsonDateSerializer;

import javax.persistence.*;
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
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "InvestFundDetailsMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = OfeDetails.class,
                                columns = {
                                        @ColumnResult(name = "name", type = String.class),
                                        @ColumnResult(name = "id", type = String.class),
                                        @ColumnResult(name = "date", type = LocalDate.class),
                                        @ColumnResult(name = "close", type = BigDecimal.class),
                                        @ColumnResult(name = "diff", type = BigDecimal.class)
                                })})
})
//@NamedNativeQueries({
//        @NamedNativeQuery(
//                name = "InvestFund.getAllWithDetails",
//                query = InvestFundNamedNativeQueries.allWithDetails,
//                resultSetMapping = "InvestFundDetailsMapping"),
//        @NamedNativeQuery(
//                name = "InvestFund.getByIdWithDetails",
//                query = InvestFundNamedNativeQueries.byIdWithDetails,
//                resultSetMapping = "InvestFundDetailsMapping")
//})


public class Ofe {

    @Id
    @NotNull
    @Size(min = 6, max = 6)
    private String id;

    @NotNull
    private String name;

    @JsonSerialize(using = JsonDateSerializer.class)
    @NotNull
    private LocalDate lastRatingDate;

    @JsonIgnore
    @OneToMany(mappedBy = "ofe", cascade = CascadeType.PERSIST)
    private List<Rating> ratings = new ArrayList<>();

    public Ofe() {
    }

    public Ofe(Ofe.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.lastRatingDate = builder.lastRatingDate;
    }

    public static class Builder {
        private String id;
        private String name;
        private LocalDate lastRatingDate;

        public Ofe.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Ofe.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Ofe.Builder withLastRatingDate(LocalDate lastRatingDate) {
            this.lastRatingDate = lastRatingDate;
            return this;
        }

        public Ofe build() {
            return new Ofe(this);
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

    public List<Rating> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return "Ofe{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastRatingDate=" + lastRatingDate +
                ", ratings=" + ratings +
                '}';
    }

}
