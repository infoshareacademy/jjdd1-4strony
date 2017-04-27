package com.isacademy.jjdd1.czterystrony;

import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoWeb;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

@SessionScoped
public class DaoService implements Serializable{

    @Inject
    InvestFundsDaoWeb investFundsDao;

    public InvestFund get(String id) throws FileNotFoundException {
        return investFundsDao.get(id);
    }

    public List<InvestFund> getAllByName() throws FileNotFoundException {
        return investFundsDao.getAllByName();
    }

    public List<InvestFund> getAllByPriority() throws FileNotFoundException {
        return investFundsDao.getAllByPriority();
    }
}
