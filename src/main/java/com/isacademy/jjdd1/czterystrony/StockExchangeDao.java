package com.isacademy.jjdd1.czterystrony;

import java.util.List;

public interface StockExchangeDao<T extends Instrument> {
    void add(T instrument);
    T get(String instrumentName);
    List<T> getAllByName();
}
