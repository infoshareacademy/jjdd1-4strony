package isacademy.jjdd1.czterystrony.reports.persistance.model;

public class StatisticsDetails {

    private String id;
    private String name;
    private int clicks;

    public StatisticsDetails(String name, String id, int clicks) {
        this.id = id;
        this.name = name;
        this.clicks = clicks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getClicks() {
        return clicks;
    }
}
