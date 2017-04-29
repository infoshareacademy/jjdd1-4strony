package com.isacademy.jjdd1.czterystrony.analysis;

import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MovingAverageProviderTest {

    private static final int PERIOD = 360;
    private static MovingAverageProvider movingAverageProvider;
    private static MovingAverage simpleAverage = new SimpleMovingAverage(TestExtrema.END_ANGLE);
    private static MovingAverage weightedAverage = new WeightedMovingAverage(TestExtrema.END_ANGLE);
    private static MovingAverage exponentialAverage = new ExponentialMovingAverage(TestExtrema.END_ANGLE);
    private static List<BigDecimal> averagesValues;

    public void setup(MovingAverage average) {
        FinancialInstrument instrument = mock(FinancialInstrument.class);
        when(instrument.getRatings()).thenReturn(TestExtrema.sinRatings());
        movingAverageProvider = new MovingAverageProvider(instrument, average);
        averagesValues = movingAverageProvider.getMovingAverageRatings().stream()
                .map(Rating::getCloseValue)
                .collect(Collectors.toList());
    }

    @Test
    public void SMA_should_not_contain_max_sin_rating() {
        setup(simpleAverage);
        assertThat(averagesValues).doesNotContain(TestExtrema.OFFSET.add(TestExtrema.SIN_MAX));
    }

    @Test
    public void SMA_should_not_contain_value_below_offset() {
        setup(simpleAverage);
        assertThat(averagesValues).allMatch(s -> s.subtract(TestExtrema.OFFSET).compareTo(BigDecimal.ZERO) >= 0);
    }

    @Test
    public void WMA_should_not_contain_max_sin_rating() {
        setup(weightedAverage);
        assertThat(averagesValues).doesNotContain(TestExtrema.OFFSET.add(TestExtrema.SIN_MAX));
    }

    @Test
    public void EMA_should_not_contain_max_sin_rating() {
        setup(exponentialAverage);
        assertThat(averagesValues).doesNotContain(TestExtrema.OFFSET.add(TestExtrema.SIN_MAX));
    }
}
