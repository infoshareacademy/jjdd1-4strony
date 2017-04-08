package com.isacademy.jjdd1.czterystrony;

import java.util.List;

public interface InvestFundsDao extends StockExchangeDao<InvestFund> {
    String DATA_DIRECTORY = "./src/main/resources/data/stockexchange/investfunds";

    @Override
    void add(InvestFund investFund);

    @Override
    InvestFund get(String investFundName);

    @Override
    List<InvestFund> getAllByName();

    List<InvestFund> getAllByPriority();
}
