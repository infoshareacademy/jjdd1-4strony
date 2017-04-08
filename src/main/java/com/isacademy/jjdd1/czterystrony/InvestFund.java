package com.isacademy.jjdd1.czterystrony;

public class InvestFund extends Instrument implements Promotable {
    private int priority;
    private String company;

    public InvestFund(String investFundName, String company) {
        super(investFundName);
        this.company = company;
        this.priority = 0;
    }

    public InvestFund(String investFundName, String company, int priority) {
        super(investFundName);
        this.company = company;
        this.priority = -priority;
    }

    public String getCompany() {
        return company;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public void promote(int priority) {
        this.priority = (priority > 0) ? -priority : 0;
    }
}
