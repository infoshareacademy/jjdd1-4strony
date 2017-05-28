package isacademy.jjdd1.czterystrony.reports.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isacademy.jjdd1.czterystrony.analysis.TimeRange;

import java.util.List;

public class PopularityWrapper<T extends Popularity> {

    @JsonProperty("popularity")
    private List<T> popularities;

    @JsonProperty("period")
    private TimeRange timeRange;

    public PopularityWrapper() {
    }

    public PopularityWrapper(List<T> popularities, TimeRange timeRange) {
        this.popularities = popularities;
        this.timeRange = timeRange;
    }

    public List<T> getPopularities() {
        return popularities;
    }

    public TimeRange getPeriod() {
        return timeRange;
    }
}
