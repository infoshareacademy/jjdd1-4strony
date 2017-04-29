package com.isacademy.jjdd1.czterystrony.analysis;

import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LocalExtremaProviderTest {

    private static final int MIN_SWING_LIMIT_IN_PCT = 10;
    private static LocalExtremaProvider localExtremaProvider;
    private static List<BigDecimal> closeValues;

    @Before
    public void setup() {
        FinancialInstrument instrument = mock(FinancialInstrument.class);
        when(instrument.getRatings()).thenReturn(TestExtrema.sinRatings());
        localExtremaProvider = new LocalExtremaProvider(instrument);
        closeValues = localExtremaProvider.findExtrema(MIN_SWING_LIMIT_IN_PCT).stream()
                .map(Rating::getCloseValue)
                .collect(Collectors.toList());
    }

    @Test
    public void local_maximum_should_be_equal_offset_plus_1() {
        assertThat(closeValues).containsOnlyOnce(TestExtrema.OFFSET.add(TestExtrema.SIN_MAX));
    }

    @Test
    public void local_minimum_should_be_equal_offset_minus_1() {
        assertThat(closeValues).containsOnlyOnce(TestExtrema.OFFSET.add(TestExtrema.SIN_MIN));
    }
}
