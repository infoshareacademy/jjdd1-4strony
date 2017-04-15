package com.isacademy.jjdd1.czterystrony.utilities;

import java.util.Arrays;
import java.util.List;

public class PeakFinder<T extends Comparable<? super T>> {
    private List<T> list;

    public PeakFinder(List<T> list) {
        if (list == null || list.size() == 0)
            throw new IllegalArgumentException("array cannot be null or 0");
        this.list = list;
    }

    public T find() {
        return findUtil(0, list.size() - 1);
    }

    private T findUtil(int left, int right) {
        int mid = (right - left) / 2 + left;
        int newLeft = mid + 1;
        int newRight = mid - 1;
        if (newLeft <= right && list.get(newLeft).compareTo(list.get(mid)) > 0)
            return findUtil(newLeft, right);
        else if (newRight >= left && list.get(newRight).compareTo(list.get(mid)) > 0)
            return findUtil(left, newRight);
        else
            return list.get(mid);
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, -20, 4, 5);
        PeakFinder<Integer> max = new PeakFinder<>(list);
        System.out.println(max.find());
    }
}