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
    public void isAuthorized(String token) {
        webClient
                .get()
                .uri("tokenCheck")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
