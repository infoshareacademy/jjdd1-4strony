package com.isacademy.jjdd1.czterystrony.instruments;

import java.math.BigDecimal;

public interface Divisible<T> {
    BigDecimal divide(T dividable);
}