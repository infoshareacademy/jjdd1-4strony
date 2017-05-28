package isacademy.jjdd1.czterystrony.reports.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ZigzagReportWrapper {

    @JsonProperty("zigzagReport")
    private List<ZigzagReport> zigzagReportList;

    public ZigzagReportWrapper() {
    }

    public ZigzagReportWrapper(List<ZigzagReport> zigzagReportList) {
        this.zigzagReportList = zigzagReportList;
    }

    public List<ZigzagReport> getPopularities() {
        return zigzagReportList;
    }
}
