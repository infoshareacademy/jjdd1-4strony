package com.isacademy.jjdd1.czterystrony;

import java.util.*;
import java.util.function.Function;

import static com.isacademy.jjdd1.czterystrony.ChoosingDateMenu.choosingDateMenu;


public class MenuOfFunds {

    public int menuOfFunds;

    {


        InvestFundDAO investFundDAO = new InvestFundDAO();
        System.out.println("Wybierz interesujący Cię fundusz: ");

        for (String investFundName : investFundDAO.getAllInvestFunds().keySet()) {
            System.out.println(investFundName);




        }
        Scanner choice = new Scanner(System.in);
        int i = choice.nextInt();
    }
}