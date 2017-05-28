package isacademy.jjdd1.czterystrony.reports.persistence.model.statistics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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

    @Column(name = "LOGIN_DATE_TIME")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime loginDateTime;

    public UserStatistics() {
    }

    public UserStatistics(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.loginDateTime = builder.loginDateTime;
    }

    public static class Builder {
        private String name;
        private String email;
        private LocalDateTime loginDateTime;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withLoginDateTime(LocalDateTime loginDateTime) {
            this.loginDateTime = loginDateTime;
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

    public LocalDateTime getLoginDateTime() {
        return loginDateTime;
    }
}
