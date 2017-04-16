package com.isacademy.jjdd1.czterystrony.utilities;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class SimpleMovingAverageTest {

    private static final int DIGITS_AFTER_COMMA = 2;
    private static final int period = 5;
    private static SimpleMovingAverage simpleMovingAverage;

    @Before
    public void setup() {
        simpleMovingAverage = new SimpleMovingAverage(period);
    }

    @Test
    public void should_get_average_equal_to_zero() {
        assertThat(simpleMovingAverage.getAverage()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void average_should_be_equal_to_added_value() {
        BigDecimal value = BigDecimal.valueOf(10, DIGITS_AFTER_COMMA);
        simpleMovingAverage.add(value);
        assertThat(simpleMovingAverage.getAverage()).isEqualTo(value);
    }
}
