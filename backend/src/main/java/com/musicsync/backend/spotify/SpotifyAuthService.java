package com.musicsync.backend.spotify;


import com.musicsync.backend.accounts.ConnectedAccount;
import com.musicsync.backend.accounts.ConnectedAccountRepository;
import com.musicsync.backend.accounts.Provider;

import ch.qos.logback.core.encoder.EchoEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tools.jackson.databind.json.JsonMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;


@Service
public class SpotifyAuthService {
    private static final String SPOTIFY_AUTHORIZE_URL = "https://accounts.spotify.com/authorize";
    private static final String SPOTIFY_TOKEN_URL = "https://accounts.spotify.com/api/token";
    private static final String SPOTIFY_ME_URL = "https://api.spotify.com/v1/me";

     private static final String SCOPES = "user-read-private playlist-read-private playlist-read-collaborative";


    private final ConnectedAccountRepository connectedAccountRepository;
    private final JsonMapper jsonMapper;
    private final HttpClient httpClient;


    @Value("${spotify.client-id}")
    private String clientId;


    @Value("${spotify.client-secret}")
    private String clientSecret;

    @Value("${spotify.redirect-uri}")
    private String redirectUri;


    public SpotifyAuthService(ConnectedAccountRepository connectedAccountRepository, JsonMapper jsonMapper){
        this.connectedAccountRepository = connectedAccountRepository;
        this.jsonMapper = jsonMapper;
        this.httpClient = HttpClient.newHttpClient();
    }

    public String buildLoginUrl(){
        if(clientId == null || clientId.isBlank()){
            throw new IllegalStateException("SPOTIFY_CLIENT_ID no está configurado");
        }

        if (redirectUri == null || redirectUri.isBlank()){
            throw new IllegalStateException("SPOTIFY_REDIRECT_URI no está configurado");
        }
        return SPOTIFY_AUTHORIZE_URL + "?response_type=code" + "&client_id=" + encode(clientId)
                + "&scope=" + encode(SCOPES)
                + "&redirect_uri=" + encode(redirectUri);
    }

    private SpotifyUserProfile getCurrentUserProfile(String accessToken) throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(SPOTIFY_ME_URL))
        .header("Authorization", "Bearer " + accessToken)
        .GET()
        .build();

        HttpResponse<String> response = httpClient.send(
                    request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() < 200 || response.statusCode() >= 300){
            throw new IllegalStateException("Error al pedir perfil de Spotify: " + response.body());
        }
        
        return jsonMapper.readValue(response.body(), SpotifyUserProfile.class);

    }


    private SpotifyTokenResponse exchangeCodeForToken(String code) throws Exception{
        String body = "grant_type=authorization_code"
                + "&code=" + encode(code)
                + "&redirect_uri=" + encode(redirectUri);
        
        

    }





    private String encode(String value) {
    return URLEncoder.encode(value, StandardCharsets.UTF_8);
}
}

