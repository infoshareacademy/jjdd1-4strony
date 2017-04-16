package com.isacademy.jjdd1.czterystrony.utilities;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class WeightedMovingAverageTest {

    private static final int DIGITS_AFTER_COMMA = 2;
    private static final int POSITIVE_PERIOD = 5;
    private static final int NEGATIVE_PERIOD = -5;
    private static final int TEST_VALUE = 10;
    private static WeightedMovingAverage weightedMovingAverage;

    @Before
    public void setup() {
        weightedMovingAverage = new WeightedMovingAverage(POSITIVE_PERIOD);
    }

    @Test
    public void should_get_average_equal_to_zero() {
        assertThat(weightedMovingAverage.getAverage()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void average_should_be_equal_to_added_value() {
        BigDecimal value = BigDecimal.valueOf(TEST_VALUE, DIGITS_AFTER_COMMA);
        weightedMovingAverage.add(value);
        assertThat(weightedMovingAverage.getAverage()).isEqualTo(value);
    }

    @Test
    public void should_failure_when_negative_period_is_given() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SimpleMovingAverage(NEGATIVE_PERIOD));
    }
}
