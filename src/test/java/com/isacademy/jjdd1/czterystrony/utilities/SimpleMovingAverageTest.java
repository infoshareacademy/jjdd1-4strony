package com.isacademy.jjdd1.czterystrony.utilities;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

public class SimpleMovingAverageTest {

    private static SimpleMovingAverage simpleMovingAverage;

    @Before
    public void setup() {
        simpleMovingAverage = new SimpleMovingAverage(TestAverages.POSITIVE_PERIOD);
    }

    @Test
    public void should_get_average_equal_to_zero() {
        assertThat(simpleMovingAverage.getAverage()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void average_for_only_1_value_should_be_equal_to_this_value() {
        BigDecimal value = BigDecimal.valueOf(TestAverages.TEST_VALUE, TestAverages.DIGITS_AFTER_COMMA);
        simpleMovingAverage.add(value);
        assertThat(simpleMovingAverage.getAverage()).isEqualTo(value);
    }

    @Test
    public void should_failure_when_negative_period_is_given() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SimpleMovingAverage(TestAverages.NEGATIVE_PERIOD));
    }
}
