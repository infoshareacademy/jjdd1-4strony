package com.isacademy.jjdd1.czterystrony.dao;

import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;

import java.util.List;

public interface InvestFundsDao extends StockExchangeDao<InvestFund> {
    String INVEST_FUNDS_LIST_DIRECTORY = "/data/stockexchange/omegafun.lst";
    String INVEST_FUNDS_DATA_FOLDER_DIRECTORY = "/data/stockexchange/investfunds/";

    @Override
    InvestFund get(String investFundId);

    @Override
    List<InvestFund> getAllByName();

    List<InvestFund> getAllByPriority();
}