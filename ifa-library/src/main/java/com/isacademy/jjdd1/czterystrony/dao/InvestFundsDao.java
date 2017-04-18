package com.isacademy.jjdd1.czterystrony.dao;

import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;

import java.util.List;

public interface InvestFundsDao extends StockExchangeDao<InvestFund> {

    @Override
    InvestFund get(String id);

    @Override
    List<InvestFund> getAllByName();

    List<InvestFund> getAllByPriority();
}