package com.isacademy.jjdd1.czterystrony.ui;

import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;

public class SelectExtremaCommand implements Command {
    public ShowLocalExtremaCommand showLocalExtremaCommand;
    public ShowGlobalExtremaCommand showGlobalExtremaCommand;
    public ReturnCommand returnCommand;

    public SelectExtremaCommand(FinancialInstrument financialInstrument) {

    }

    @Override
    public void execute() {

    }
}
