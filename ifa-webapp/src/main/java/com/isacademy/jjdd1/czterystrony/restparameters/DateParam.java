package com.isacademy.jjdd1.czterystrony.restparameters;

import javax.ws.rs.WebApplicationException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateParam {

    private LocalDate date;

    public DateParam(String date) throws WebApplicationException {
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new WebApplicationException(e);
        }
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "DateParam{" +
                "date=" + date +
                '}';
    }
}
