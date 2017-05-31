package com.isacademy.jjdd1.czterystrony.users;

import com.isacademy.jjdd1.czterystrony.CompanyMail;

public enum UserType {
    ADMIN(CompanyMail.ADDRESS),
    ADMINiSA("isaloginjava@gmail.com"),
    GOOGLE_USER("@gmail.com");

    private final String email;

    UserType(String email) {
        this.email = email;
    }

    public String getEmailPattern() {
        return email;
    }
}
