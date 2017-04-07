package com.isacademy.jjdd1.czterystrony;

import java.util.List;

public interface StockExchangeDAO<T extends Instrument> {
    void add(T instrument);
    void delete(T instrument);
    T get(String instrumentName);
    List<T> getAll();
}
