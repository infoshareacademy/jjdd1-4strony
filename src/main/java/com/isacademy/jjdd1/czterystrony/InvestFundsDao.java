package com.isacademy.jjdd1.czterystrony;

import java.util.List;

public interface InvestFundsDao extends StockExchangeDao<InvestFund> {
    String INVEST_FUNDS_LIST_DIRECTORY = "./src/main/resources/data/stockexchange/omegafun.lst";
    String INVEST_FUNDS_DATA_FOLDER_DIRECTORY = "./src/main/resources/data/stockexchange/investfunds";
    int BEGIN_OF_INVEST_FUND_ID = 33;
    int END_OF_INVEST_FUND_ID = 39;
    int BEGIN_OF_INVEST_FUND_NAME = 51;

    @Override
    InvestFund get(String investFundId);

    @Override
    List<InvestFund> getAllByName();

    List<InvestFund> getAllByPriority();
}