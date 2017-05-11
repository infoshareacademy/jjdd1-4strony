package com.isacademy.jjdd1.czterystrony.users;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/4analysis/login")
public class LoginServlet extends HttpServlet {

    private final String CLIENT_ID = "280540127427-i5fcabv2i9poto8co2niio2hf4m2cg7k.apps.googleusercontent.com";
    private final String CLIENT_SECRET = "2jPDzoqCSw74HOvUpLMipB3w";
    private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v2/userinfo";

    private final OAuth20Service service = new ServiceBuilder()
            .apiKey(CLIENT_ID)
            .apiSecret(CLIENT_SECRET)
            .scope("profile")
            .scope("email")
            .callback("http://localhost:8080/4analysis/login")
            .build(GoogleApi20.instance());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

//        final Map<String, String> additionalParams = new HashMap<>();
//        additionalParams.put("access_type", "offline");
//        //force to reget refresh token (if usera are asked not the first time)
//        additionalParams.put("prompt", "consent");
//        final String authorizationUrl = service.getAuthorizationUrl(additionalParams);
//        System.out.println("Got the Authorization URL!");
//        System.out.println("Now go and authorize ScribeJava here:");
//        System.out.println(authorizationUrl);
//        System.out.println("And paste the authorization code here");
//        final String code = in.nextLine();
//
//        System.out.println("And paste the state from server here. We have set 'secretState'='" + secretState + "'.");
//        final String value = in.nextLine();
//        if (secretState.equals(value)) {
//            System.out.println("State value does match!");
//        } else {
//            System.out.println("Ooops, state value does not match!");
//            System.out.println("Expected = " + secretState);
//            System.out.println("Got      = " + value);
//        }
//
//        // Trade the Request Token and Verfier for the Access Token
//        System.out.println("Trading the Request Token for an Access Token...");
//        OAuth2AccessToken accessToken = service.getAccessToken(code);
//        System.out.println("Got the Access Token!");
//        System.out.println("(if your curious it looks like this: " + accessToken
//                + ", 'rawResponse'='" + accessToken.getRawResponse() + "')");
//
//        System.out.println("Refreshing the Access Token...");
//        accessToken = service.refreshAccessToken(accessToken.getRefreshToken());
//        System.out.println("Refreshed the Access Token!");
//        System.out.println("(if your curious it looks like this: " + accessToken
//                + ", 'rawResponse'='" + accessToken.getRawResponse() + "')");
//
//        // Now let's go and ask for a protected resource!
//        System.out.println("Now we're going to access a protected resource...");
//        while (true) {
//            System.out.println("Paste fieldnames to fetch (leave empty to get profile, 'exit' to stop example)");
//            final String query = in.nextLine();
//
//            final String requestUrl;
//            if ("exit".equals(query)) {
//                break;
//            } else if (query == null || query.isEmpty()) {
//                requestUrl = PROTECTED_RESOURCE_URL;
//            } else {
//                requestUrl = PROTECTED_RESOURCE_URL + "?fields=" + query;
//            }
//
//            final OAuthRequest request = new OAuthRequest(Verb.GET, requestUrl);
//            service.signRequest(accessToken, request);
//            final Response response = service.execute(request);
//            System.out.println(response.getCode());
//            System.out.println(response.getBody());
//        }
    }
}
