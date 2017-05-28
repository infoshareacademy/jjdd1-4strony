package isacademy.jjdd1.czterystrony.reports.persistence.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalDate;
import java.time.LocalTime;

public class UserActivityReport {

    private String name;

    private String email;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;

    private int loginCount;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate lastLoginDate;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime lastLoginTime;

    public UserActivityReport() {
    }

    public UserActivityReport(String name,
                              String email,
                              LocalDate date,
                              int loginCount,
                              LocalDate lastLoginDate,
                              LocalTime lastLoginTime) {
        this.name = name;
        this.email = email;
        this.date = date;
        this.loginCount = loginCount;
        this.lastLoginDate = lastLoginDate;
        this.lastLoginTime = lastLoginTime;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public LocalDate getLastLoginDate() {
        return lastLoginDate;
    }

    public LocalTime getLastLoginTime() {
        return lastLoginTime;
    }
}
