package com.isacademy.jjdd1.czterystrony.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.isacademy.jjdd1.czterystrony.clients.UserStatisticsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
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

    @Inject
    SessionData sessionData;

    @Inject
    UserStatisticsSender statisticsSender;

    @GET
    @Path("/signin")
    public Response login(@HeaderParam("Referer") String referer,
                          @Context HttpServletRequest request) {

        String contextRoot = getContextRoot(request);
        String callbackUrl = contextRoot + "/api/google/callback";
        String indexUrl = contextRoot + "/4analysis";
        OAuth20Service service = getOAuth20Service(callbackUrl);
        final Map<String, String> additionalParams = new HashMap<>();
        additionalParams.put("access_type", "offline");
        additionalParams.put("prompt", "consent");
        String authorizationUrl = service.getAuthorizationUrl(additionalParams);
        sessionData.setReferer(referer);
        sessionData.setCallbackUrl(callbackUrl);
        sessionData.setIndexUrl(indexUrl);
        sessionData.setOAuth20Service(service);
        return Response.seeOther(URI.create(authorizationUrl)).header("MyReferer", referer).build();
    }

    @GET
    @Path("/callback")
    public Response callback(@QueryParam("code") String code) {
        String indexUrl = sessionData.getIndexUrl();
        try {
            OAuth20Service service = sessionData.getOAuth20Service();
            OAuth2AccessToken accessToken = service.getAccessToken(code);
            OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
            service.signRequest(accessToken, request);
            String responseBody = service.execute(request).getBody();
            GoogleUser googleUser = new ObjectMapper().readValue(responseBody, GoogleUser.class);
            sessionData.logUser(googleUser);
            statisticsSender.send(googleUser.getName(), googleUser.getEmail());
            log.info("User {} logged in.", googleUser.getEmail());
            return Response.seeOther(URI.create(sessionData.getReferer())).build();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("User could not log in");
            return Response.temporaryRedirect(URI.create(indexUrl)).build();
        }
    }

    @GET
    @Path("/signout")
    public Response logout() {
        sessionData.logout();
        log.info("User {} logged out.", sessionData.getUser().getEmail());
        return Response.temporaryRedirect(URI.create(sessionData.getIndexUrl())).build();
    }

    private String getContextRoot(HttpServletRequest request) {
        return request.getScheme() + "://" +
                request.getServerName() + ":" +
                request.getServerPort();
    }

    private OAuth20Service getOAuth20Service(String callbackUrl) {
        return new ServiceBuilder()
                .apiKey(GOOGLE_CLIENT_ID)
                .apiSecret(GOOGLE_CLIENT_SECRET)
                .scope("profile")
                .scope("email")
                .callback(callbackUrl)
                .build(GoogleApi20.instance());
    }
}
