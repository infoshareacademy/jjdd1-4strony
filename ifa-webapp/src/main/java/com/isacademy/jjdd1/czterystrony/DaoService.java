package com.isacademy.jjdd1.czterystrony;

import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.util.List;

@SessionScoped
public class DaoService {

    private final static Logger log = LoggerFactory.getLogger(DaoService.class);
    @Inject
    InvestFundsDaoTxt investFundsDaoTxt;


    public InvestFund get(String id) throws FileNotFoundException {
        log.info("Fund loaded {}", id);
        return investFundsDaoTxt.get(id);


    }
    public List<InvestFund> getAllByName() throws FileNotFoundException {
        return investFundsDaoTxt.getAllByName();
    }

    public List<InvestFund> getAllByPriority() throws FileNotFoundException {
        return investFundsDaoTxt.getAllByPriority();
    }
}
