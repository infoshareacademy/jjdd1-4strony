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
public class InvestFund {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotNull
    @Size(min = 6, max = 6)
    private String shortName;

    @NotNull
    private String fullName;

    @NotNull
    private LocalDate lastRatingDate;

    @Min(0)
    @Max(100)
    @NotNull
    private int priority;

    public InvestFund() {
    }

    @OneToMany(mappedBy = "investFund", cascade = CascadeType.PERSIST)
    private List<Rating> ratings = new ArrayList<>();

    @Override
    public String toString() {
        return "InvestFund{" +
                "id=" + id +
                ", shortName='" + shortName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", lastRatingDate=" + lastRatingDate +
                ", priority=" + priority +
                '}';
    }
}
