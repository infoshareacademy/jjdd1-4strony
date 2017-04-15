package com.isacademy.jjdd1.czterystrony.instruments;

import java.math.BigDecimal;

public interface Subtractable<T> {
    BigDecimal subtract(T subtractable);
}
