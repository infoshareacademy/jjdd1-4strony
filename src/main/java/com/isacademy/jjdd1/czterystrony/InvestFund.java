package com.isacademy.jjdd1.czterystrony;

public class InvestFund extends Instrument implements Promotable, Comparable<InvestFund> {
    private int priority = 0;
    private String company;

    public InvestFund(String name, String company) {
        super(name);
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public void promote(int priority) {
        this.priority = (priority < 0) ? priority : 0;
    }

    @Override
    public int compareTo(InvestFund investFund) {
        return getName().compareTo(investFund.getName()) + priority * 100;
    }
}
