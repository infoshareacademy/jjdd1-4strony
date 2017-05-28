package isacademy.jjdd1.czterystrony.reports.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isacademy.jjdd1.czterystrony.analysis.TimeRange;

import java.util.List;

public class ZigzagReportWrapper {

    @JsonProperty("zigzagReport")
    private List<InvestFundZigzagReport> zigzagReportList;

    @JsonProperty("period")
    private TimeRange timeRange;

    public ZigzagReportWrapper() {
    }

    public ZigzagReportWrapper(List<InvestFundZigzagReport> zigzagReportList, TimeRange timeRange) {
        this.zigzagReportList = zigzagReportList;
        this.timeRange = timeRange;
    }

    public List<InvestFundZigzagReport> getZigzagReportList() {
        return zigzagReportList;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }
}
