package com.isacademy.jjdd1.czterystrony.analysis;

import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GlobalExtremaProviderTest {

    private static GlobalExtremaProvider globalExtremaProvider;

    @Before
    public void setup() {
        FinancialInstrument instrument = mock(FinancialInstrument.class);
        when(instrument.getRatings()).thenReturn(TestExtrema.sinRatings());
        globalExtremaProvider = new GlobalExtremaProvider(instrument);
    }

    @Test
    public void global_maximum_should_be_equal_offset_plus_1() {
        BigDecimal maximum = globalExtremaProvider.getGlobalExtrema().get(1).getCloseValue();
        assertThat(maximum).isEqualTo(TestExtrema.OFFSET.add(TestExtrema.SIN_MAX));
    }

    @Test
    public void global_minimum_should_be_equal_offset_minus_1() {
        BigDecimal minimum = globalExtremaProvider.getGlobalExtrema().get(0).getCloseValue();
        assertThat(minimum).isEqualTo(TestExtrema.OFFSET.add(TestExtrema.SIN_MIN));
    }
}
