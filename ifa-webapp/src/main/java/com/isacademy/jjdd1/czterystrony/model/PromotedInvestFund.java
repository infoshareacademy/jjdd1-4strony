package com.isacademy.jjdd1.czterystrony.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class PromotedInvestFund {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotNull
    @Size(min = 6, max =6)
    private String investFundId;

    @Min(0)
    @Max(100)
    @NotNull
    private int priority;

    public PromotedInvestFund() {
    }

    public PromotedInvestFund(String investFundId, int priority) {
        this.investFundId = investFundId;
        this.priority = priority;
    }

    public String getInvestFundId() {
        return investFundId;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "PromotedInvestFund{" +
                ", investFundId='" + investFundId + '\'' +
                ", priority=" + priority +
                '}';
    }
}
