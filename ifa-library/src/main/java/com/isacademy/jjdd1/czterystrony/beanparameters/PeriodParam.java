package com.isacademy.jjdd1.czterystrony.beanparameters;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.isacademy.jjdd1.czterystrony.serializers.JsonDateSerializer;

import java.time.LocalDate;

public interface PeriodParam {
    @JsonProperty("start")
    @JsonSerialize(using = JsonDateSerializer.class)
    LocalDate startDate();

    @JsonProperty("end")
    @JsonSerialize(using = JsonDateSerializer.class)
    LocalDate endDate();
}
