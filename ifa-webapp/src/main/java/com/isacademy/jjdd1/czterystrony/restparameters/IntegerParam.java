package com.isacademy.jjdd1.czterystrony.restparameters;

import javax.ws.rs.WebApplicationException;

public class IntegerParam {

    private int number;

    public IntegerParam(String number) throws WebApplicationException {
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
