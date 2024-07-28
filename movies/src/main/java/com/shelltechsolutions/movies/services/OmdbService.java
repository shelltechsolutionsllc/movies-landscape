package com.shelltechsolutions.movies.services;

import com.shelltechsolutions.movies.feignclients.OmdbFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

@Service
public class OmdbService {

    @Value("${omdb.api.key}")
    String apiKey;

    public String getMovieById(String id) {
        return WebClient
                        .create("https://omdbapi.com/")
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .queryParam("i", id)
                                .queryParam("apikey", apiKey)
                                .build())
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
    }

    public String searchMoviesByTitle(String title) {
        return WebClient
                        .create("https://omdbapi.com/")
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .queryParam("s", title)
                                .queryParam("apikey", apiKey)
                                .build())
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
    }
}
