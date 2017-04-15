package com.isacademy.jjdd1.czterystrony.utilities;

import java.util.Collections;
import java.util.List;

public class Shifter {
    public static <T> List<T> shift(List<T> list, int shift) {
        Collections.rotate(list, shift);
        int absoluteShift = Math.abs(shift);

        try {
            return list.subList(absoluteShift, list.size() - absoluteShift);
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }
}
