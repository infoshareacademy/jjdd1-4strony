package com.isacademy.jjdd1.czterystrony.dao;

import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;

import java.io.FileNotFoundException;
import java.util.List;

public interface StockExchangeDao<T extends FinancialInstrument> {

    T get(String id) throws FileNotFoundException;

    List<T> getAllByName() throws FileNotFoundException;
}