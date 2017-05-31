package com.isacademy.jjdd1.czterystrony.users;

import com.github.scribejava.core.oauth.OAuth20Service;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class SessionData implements Serializable {

    private boolean logged;
    private GoogleUser user;
    private String referer;
    private UserType userType;
    private boolean admin;
    private OAuth20Service service;
    private String callbackUrl;
    private String indexUrl;

    public void logUser(GoogleUser user) {
        this.user = user;
        this.logged = true;
        setUserType();
    }

    private void setUserType() {
        String userEmail = user.getEmail();

        if (UserType.ADMIN.getEmailPattern().equals(userEmail) ||
                UserType.ADMINiSA.getEmailPattern().equals(userEmail)) {
            userType = UserType.ADMIN;
            admin = true;
            return;
        }
        userType = UserType.GOOGLE_USER;
        admin = false;
    }

    public void logout() {
        this.logged = false;
        this.admin = false;
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

    public OAuth20Service getOAuth20Service() {
        return service;
    }

    public void setOAuth20Service(OAuth20Service service) {
        this.service = service;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }
}
