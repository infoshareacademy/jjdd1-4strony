package com.isacademy.jjdd1.czterystrony.analysis;

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
    public void when_nothing_added_average_should_be_zero() {
        assertThat(simpleMovingAverage.getAverage()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void average_for_only_1_value_should_be_equal_this_value() {
        simpleMovingAverage.add(TestAverages.TEST_VALUE);
        assertThat(simpleMovingAverage.getAverage()).isEqualTo(TestAverages.TEST_VALUE);
    }

    @Test
    public void should_failure_when_negative_period_is_given() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SimpleMovingAverage(TestAverages.NEGATIVE_PERIOD));
    }
}
