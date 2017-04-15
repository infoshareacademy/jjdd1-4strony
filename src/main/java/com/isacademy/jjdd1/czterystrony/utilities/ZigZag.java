package com.isacademy.jjdd1.czterystrony.utilities;

import com.isacademy.jjdd1.czterystrony.instruments.Subtractable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZigZag {
    public static <T extends Subtractable<? super T>> List<T> zigZag(List<T> list, int obsStart, int obsEnd, double minSwingPct) {
        boolean swingHigh = false;
        boolean swingLow = false;

        int obsLow = obsStart;
        int obsHigh = obsStart;

        List<T> zigZag = new ArrayList<>();

        for (int obs = obsStart; obs <= obsEnd; obs++) {
            if (list.get(obs).subtract(list.get(obsHigh)).compareTo(BigDecimal.ZERO) == 1) {
                obsHigh = obs;

                if (!swingLow && ((list.get(obsHigh).subtract(list.get(obsLow))).divide(list.get(obsLow)) >= minSwingPct/100D) {
                    zigZag.add(list.get(obsLow));  // new swinglow
                    swingHigh = false;
                    swingLow = true;
                }

                if (swingLow) obsLow = obsHigh;

            } else if (list.get(obs).subtract(list.get(obsLow)).compareTo(BigDecimal.ZERO) == -1) {
                obsLow = obs;

                if (!swingHigh && ((list.get(obsHigh) - list.get(obsLow)) / list.get(obsLow)) >= minSwingPct/100D) {
                    zigZag.add(list.get(obsHigh));  // new swinghigh
                    swingHigh = true;
                    swingLow = false;
                }
                if (swingHigh) obsHigh = obsLow;
            }
        }
        return zigZag;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 4, 3, 2, 1, 5, 6, 8, 10, 13, 10, 5, 3, 2, 4, 6, 7, 8);

        System.out.println(list.size());

        List<Integer> zigzag = ZigZag.zigZag(list, 0, 22, 99F);

        System.out.println(zigzag.size());

        for (Integer pos : zigzag) {
            System.out.println("pozycja: " + pos + "  wartość: " + list.get(pos));
        }
    }
}
