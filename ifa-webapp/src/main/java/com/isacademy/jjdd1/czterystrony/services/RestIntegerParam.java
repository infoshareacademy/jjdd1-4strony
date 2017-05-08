package com.isacademy.jjdd1.czterystrony.services;

import javax.ws.rs.WebApplicationException;
import javax.xml.ws.WebServiceException;

public class RestIntegerParam {

    private int number;

    public RestIntegerParam(String number) throws WebApplicationException {
        try {
            this.number = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new WebServiceException(e);
        }
    }

    public int getNumber() {
        return number;
    }
}
