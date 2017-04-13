package com.isacademy.jjdd1.czterystrony.utilities;

public enum Extremum {
    MINIMUM(-1),
    MAXIMUM(1);

    private final int value;

    Extremum(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

