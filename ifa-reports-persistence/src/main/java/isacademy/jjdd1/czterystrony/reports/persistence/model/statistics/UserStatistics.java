package isacademy.jjdd1.czterystrony.reports.persistence.model.statistics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import isacademy.jjdd1.czterystrony.reports.persistence.model.InvestFundPopularity;
import isacademy.jjdd1.czterystrony.reports.persistence.model.InvestFundZigzagReport;
import isacademy.jjdd1.czterystrony.reports.persistence.model.UserActivityReport;
import isacademy.jjdd1.czterystrony.reports.persistence.queries.ReportQueries;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "UserActivityReportMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = UserActivityReport.class,
                                columns = {
                                        @ColumnResult(name = "name", type = String.class),
                                        @ColumnResult(name = "email", type = String.class),
                                        @ColumnResult(name = "date", type = LocalDate.class),
                                        @ColumnResult(name = "loginCount", type = int.class),
                                        @ColumnResult(name = "lastLoginDate", type = LocalDate.class),
                                        @ColumnResult(name = "lastLoginTime", type = LocalTime.class),
                                }
                        )})
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "UserActivityReport.getAll",
                query = ReportQueries.getUserActivityReport,
                resultSetMapping = "UserActivityReportMapping")
})
public class UserStatistics {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "LOGIN_DATE")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate loginDate;

    @Column(name = "LOGIN_TIME")
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime loginTime;


    public UserStatistics() {
    }

    public UserStatistics(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.loginDate = builder.loginDate;
        this.loginTime = builder.loginTime;
    }

    public static class Builder {
        private String name;
        private String email;
        private LocalDate loginDate;
        private LocalTime loginTime;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withLoginDate(LocalDate loginDate) {
            this.loginDate = loginDate;
            return this;
        }

        public Builder withLoginTime(LocalTime loginTime) {
            this.loginTime = loginTime;
            return this;
        }

        public UserStatistics build() {
            return new UserStatistics(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getLoginDate() {
        return loginDate;
    }

    public LocalTime getLoginTime() {
        return loginTime;
    }
}
