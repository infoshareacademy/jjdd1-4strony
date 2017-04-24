package com.isacademy.jjdd1.czterystrony.dao;

import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;

import java.io.FileNotFoundException;
import java.util.List;

public interface InvestFundsDao extends StockExchangeDao<InvestFund> {

    @Override
    InvestFund get(String id) throws FileNotFoundException;

    @Override
    List<InvestFund> getAllByName() throws FileNotFoundException;

    List<InvestFund> getAllByPriority() throws FileNotFoundException;
}