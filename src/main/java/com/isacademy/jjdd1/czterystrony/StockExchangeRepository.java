package com.isacademy.jjdd1.czterystrony;

import java.util.List;

public interface StockExchangeDAO<T extends Instrument> {
    boolean add(T instrument);
    B delete(T instrument);
    T get(String instrumentName);
    List<T> getAll();
}
