package com.isacademy.jjdd1.czterystrony.database;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@javax.persistence.Entity
@javax.persistence.Table

public class Statistics {
    @Id
    @GeneratedValue
    private Long id;

    private String fund;

    private String extremas;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String User;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public String getExtremas() {
        return extremas;
    }

    public void setExtremas(String extremas) {
        this.extremas = extremas;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", fund='" + fund + '\'' +
                ", extremas='" + extremas + '\'' +
                ", date=" + date +
                ", User='" + User + '\'' +
                '}';
    }
}
