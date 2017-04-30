package com.isacademy.jjdd1.czterystrony.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDate date;

    @Digits(integer=6, fraction=2)
    private BigDecimal open;

    @Digits(integer=6, fraction=2)
    private BigDecimal high;

    @Digits(integer=6, fraction=2)
    private BigDecimal low;

    @Digits(integer=6, fraction=2)
    private BigDecimal close;

    @ManyToOne
    private InvestFund investFund;

    public Rating() {
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", date=" + date +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", investFund=" + investFund +
                '}';
    }
}
