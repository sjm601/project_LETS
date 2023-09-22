package com.vj.lets.domain.member.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Person;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;

@Service
@Slf4j
public class GoogleLoginService {

    private static final String CALLBACK_URI = "http://localhost/member/callback";
    private static final Collection<String> SCOPES = Arrays.asList("profile", "email");
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static HttpTransport httpTransport;

    @Value("src/main/resources/client_secret.json")
    private String googleJson;

    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (httpTransport == null) {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        }

        InputStream in = new FileInputStream(googleJson);

        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES).build();

        GenericUrl url =
                flow.newAuthorizationUrl().setRedirectUri(CALLBACK_URI).setState("state-token");

        response.sendRedirect(url.build());

    }

    public GoogleTokenResponse getToken(String code) throws Exception {
        log.info("==============================={}", googleJson);
        InputStream in = new FileInputStream(googleJson);
        log.info("==============================={}", in);
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        log.info("==============================={}", clientSecrets);
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES).build();
        log.info("==============================={}", flow);
        return flow.newTokenRequest(code).setRedirectUri(CALLBACK_URI).execute();
    }

    public Person getUserInfo(String accessToken) throws IOException {
        Credential credential = new GoogleCredential().setAccessToken(accessToken);
        PeopleService peopleService =
                new PeopleService.Builder(httpTransport, JacksonFactory.getDefaultInstance(), credential)
                        .setApplicationName("lets")
                        .build();
        Person profile = peopleService.people().get("people/me")
                .setPersonFields("names,emailAddresses")
                .execute();
        return profile;
    }

}
