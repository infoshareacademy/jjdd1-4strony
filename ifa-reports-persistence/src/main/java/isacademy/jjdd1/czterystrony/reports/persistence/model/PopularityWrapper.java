package isacademy.jjdd1.czterystrony.reports.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isacademy.jjdd1.czterystrony.beanparameters.PeriodParam;

import java.util.List;

public class PopularityWrapper<T extends  Popularity> {

    @JsonProperty("popularity")
    private List<T> popularities;

    @JsonProperty("period")
    private PeriodParam period;

    public PopularityWrapper(List<T> popularities, PeriodParam period) {
        this.popularities = popularities;
        this.period = period;
    }

    public List<T> getPopularities() {
        return popularities;
    }

    public PeriodParam getPeriod() {
        return period;
    }
}
