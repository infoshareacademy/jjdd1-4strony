package com.isacademy.jjdd1.czterystrony.utilities;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ExponentialMovingAverageTest {

    private static ExponentialMovingAverage exponentialMovingAverage;

    @Before
    public void setup() {
        exponentialMovingAverage = new ExponentialMovingAverage(TestAverages.POSITIVE_PERIOD);
    }

    @Test
    public void should_get_average_equal_to_zero() {
        assertThat(exponentialMovingAverage.getAverage()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void average_for_only_1_value_should_be_equal_to_this_value() {
        exponentialMovingAverage.add(TestAverages.TEST_VALUE);
        assertThat(exponentialMovingAverage.getAverage()).isEqualTo(TestAverages.TEST_VALUE);
    }

    @Test
    public void should_failure_when_negative_period_is_given() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SimpleMovingAverage(TestAverages.NEGATIVE_PERIOD));
    }
}
