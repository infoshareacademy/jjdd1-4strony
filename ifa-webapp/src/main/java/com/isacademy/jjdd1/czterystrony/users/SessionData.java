package com.isacademy.jjdd1.czterystrony.users;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class SessionData implements Serializable {

    private boolean logged = false;
    private GoogleUser user;
    private String referer;
    private UserType userType;
    private boolean admin = false;

    public void logUser(GoogleUser user) {
        this.user = user;
        this.logged = true;
        setUserType();
    }

    private void setUserType() {
        String userEmail = user.getEmail();

        if (UserType.ADMIN.getEmailPattern().equals(userEmail)) {
            userType = UserType.ADMIN;
            admin = true;
            return;
        }

        userType = UserType.GOOGLE_USER;
    }

    public void logout() {
        this.logged = false;
    }

    public boolean isLogged() {
        return logged;
    }

    public GoogleUser getUser() {
        return user;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public UserType getUserType() {
        return userType;
    }

    public boolean isAdmin() {
        return admin;
    }
}
