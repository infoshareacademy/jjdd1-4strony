package isacademy.jjdd1.czterystrony.reports.persistance.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.isacademy.jjdd1.czterystrony.serializers.JsonDateSerializer;

import java.time.LocalDate;
import java.util.List;

public class PopularityWrapper {

    @JsonProperty("popularity")
    private List<Popularity> popularities;

    @JsonProperty("date")
    @JsonSerialize(using = JsonDateSerializer.class)
    private LocalDate reportDate;

    public List<Popularity> getPopularities() {
        return popularities;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }
}
