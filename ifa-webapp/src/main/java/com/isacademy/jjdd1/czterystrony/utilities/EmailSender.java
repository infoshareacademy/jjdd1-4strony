package com.isacademy.jjdd1.czterystrony.utilities;

public class EmailSender {

    public static void main(String[] args) {
        EmailUtility emailUtility = new EmailUtility();

        emailUtility.sendEmail("4analysisteam@gmail.com", "nosubject", "tresc maila");
    }

}
