package com.isacademy.jjdd1.czterystrony.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {@Index(
        name = "UX_InvestFund_id_fullName",
        columnList = "id,fullName")
})
public class InvestFund {

    @Id
    @NotNull
    @Size(min = 6, max = 6)
    private String id;

    @NotNull
    private String fullName;

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
        this.fullName = builder.fullName;
        this.lastRatingDate = builder.lastRatingDate;
    }

    public static class Builder {
        private String id;
        private String fullName;
        private LocalDate lastRatingDate;

        public InvestFund.Builder withId(String id) {
            this.id = id;
            return this;
        }

        public InvestFund.Builder withFulltName(String fullName) {
            this.fullName = fullName;
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

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
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
                ", fullName='" + fullName + '\'' +
                ", lastRatingDate=" + lastRatingDate +
                ", priority=" + priority +
                '}';
    }
}
