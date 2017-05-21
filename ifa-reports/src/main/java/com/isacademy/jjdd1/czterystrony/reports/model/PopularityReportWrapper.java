package com.isacademy.jjdd1.czterystrony.reports.model;

import java.time.LocalDate;
import java.util.List;

public class PopularityReportWrapper {
    private List<PopularityDTO> popularityDTOs;
    private LocalDate reportDate;

    public List<PopularityDTO> getPopularityDTOs() {
        return popularityDTOs;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }
}
