package com.isacademy.jjdd1.czterystrony.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class Statistics {
    @Id
    @GeneratedValue
    private Long id;

    private String investFundId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private int zigZag;

    private double averageDateDifference;

    private double averageValueDifference;

    private String user;

    public Statistics() {
    }

    public double getAverageDateDifference() {
        return averageDateDifference;
    }

    public void setAverageDateDifference(double averageDateDifference) {
        this.averageDateDifference = averageDateDifference;
    }

    public double getAverageValueDifference() {
        return averageValueDifference;
    }

    public void setAverageValueDifference(double averageValueDifference) {
        this.averageValueDifference = averageValueDifference;
    }

    public int getZigZag() {
        return zigZag;
    }

    public void setZigZag(int zigZag) {
        this.zigZag = zigZag;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvestFundId() {
        return investFundId;
    }

    public void setInvestFundId(String fund) {
        this.investFundId = fund;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", fund='" + investFundId + '\'' +
                ", date=" + date +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", zigZag=" + zigZag +
                ", averageDateDifference=" + averageDateDifference +
                ", averageValueDifference=" + averageValueDifference +
                ", user='" + user + '\'' +
                '}';
    }
}