package com.isacademy.jjdd1.czterystrony.services;

import javax.ws.rs.WebApplicationException;

public class RestIntegerParam {

    private int number;

    public RestIntegerParam(String number) throws WebApplicationException {
        try {
            this.number = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new WebApplicationException(e);
        }
    }

    public int getNumber() {
        return number;
    }
}
