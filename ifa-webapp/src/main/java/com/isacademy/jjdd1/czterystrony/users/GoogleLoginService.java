package com.isacademy.jjdd1.czterystrony.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Path("/google")
public class GoogleLoginService {

    private static Logger log = LoggerFactory.getLogger(GoogleLoginService.class);
    private static final String GOOGLE_CLIENT_ID = "280540127427-i5fcabv2i9poto8co2niio2hf4m2cg7k.apps.googleusercontent.com";
    private static final String GOOGLE_CLIENT_SECRET = "2jPDzoqCSw74HOvUpLMipB3w";
    private static final String PROTECTED_RESOURCE_URL = "https://www.googleapis.com/oauth2/v2/userinfo";
    private static final String CALLBACK_URL = "http://localhost:8080/api/google/callback";
    private static final String INDEX_URL = "http://localhost:8080/4analysis";

    private OAuth20Service service = new ServiceBuilder()
            .apiKey(GOOGLE_CLIENT_ID)
            .apiSecret(GOOGLE_CLIENT_SECRET)
            .scope("profile")
            .scope("email")
            .callback(CALLBACK_URL)
            .build(GoogleApi20.instance());

    @Inject
    SessionData sessionData;

    @GET
    @Path("/signin")
    public Response login(@HeaderParam("Referer") String referer) {
        final Map<String, String> additionalParams = new HashMap<>();
        additionalParams.put("access_type", "offline");
        additionalParams.put("prompt", "consent");
        sessionData.setReferer(referer);
        String authorizationUrl = service.getAuthorizationUrl(additionalParams);
        return Response.seeOther(URI.create(authorizationUrl)).header("MyReferer", referer).build();
    }

    @GET
    @Path("/callback")
    public Response callback(@QueryParam("code") String code) {

        try {
            OAuth2AccessToken accessToken = service.getAccessToken(code);
            OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
            service.signRequest(accessToken, request);
            String responseBody = service.execute(request).getBody();
            GoogleUser googleUser = new ObjectMapper().readValue(responseBody, GoogleUser.class);
            sessionData.logUser(googleUser);
            log.info("User {} logged in.", googleUser.getEmail());
            return Response.seeOther(URI.create(sessionData.getReferer())).build();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("User could not log in");
            return Response.temporaryRedirect(URI.create(INDEX_URL)).build();
        }
    }

    @GET
    @Path("/signout")
    public Response logout(@HeaderParam("Referer") String referer) {
        sessionData.logout();
        log.info("User {} logged out.", sessionData.getUser().getEmail());
        return Response.temporaryRedirect(URI.create(referer)).build();
    }
}
