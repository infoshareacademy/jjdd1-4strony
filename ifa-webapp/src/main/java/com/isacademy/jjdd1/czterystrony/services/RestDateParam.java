package com.isacademy.jjdd1.czterystrony.services;

import javax.ws.rs.WebApplicationException;
import javax.xml.ws.WebServiceException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class RestDateParam {

    private LocalDate date;

    public RestDateParam(String date) throws WebApplicationException {
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new WebServiceException(e);
        }
    }

    public LocalDate getDate() {
        return date;
    }
}
