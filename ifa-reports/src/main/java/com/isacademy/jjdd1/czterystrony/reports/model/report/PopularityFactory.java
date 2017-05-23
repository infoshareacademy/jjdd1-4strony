package com.isacademy.jjdd1.czterystrony.reports.model.report;

import java.time.LocalDate;

public class PopularityFactory {
    public static <T extends Popularity> T create(T popularity, PopularityDTO dto, LocalDate date) {
        popularity.setDate(date);
        popularity.setInstrumentId(dto.getInstrumentId());
        popularity.setInstrumentName(dto.getInstrumentName());
        popularity.setClicks(dto.getClicks());
        return popularity;
    }
}
