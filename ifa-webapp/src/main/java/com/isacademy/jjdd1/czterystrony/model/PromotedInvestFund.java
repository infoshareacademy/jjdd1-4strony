package com.isacademy.jjdd1.czterystrony.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class PromotedInvestFund {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 6, max =6)
    private String investFundId;

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
