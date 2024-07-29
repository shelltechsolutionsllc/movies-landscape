package com.shelltechsolutions.movies.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthenticationService {

    private final WebClient webClient;

    public AuthenticationService(@Value("${auth.api.url}") String authUrl) {
        webClient = WebClient
                .builder()
                .baseUrl(authUrl)
                .build();
    }
    public Boolean isAuthorized(String token) {
        Token isTokenValid = webClient
                .get()
                .uri("isTokenValid")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                .retrieve()
                .bodyToMono(Token.class)
                .block();

        return isTokenValid.isValid();
    }

    public record Token(Boolean isValid) {
    }
}
