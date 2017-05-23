package com.isacademy.jjdd1.czterystrony.reports.model.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;
import java.util.List;

public class PopularityReportWrapper {

    @JsonProperty("report")
    private List<PopularityDTO> popularityDTOs;

    @JsonProperty("date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate reportDate;

    public List<PopularityDTO> getPopularityDTOs() {
        return popularityDTOs;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }
}
