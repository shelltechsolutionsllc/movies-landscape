package com.shelltechsolutions.movies.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shelltechsolutions.movies.controllers.dto.Movie;
import com.shelltechsolutions.movies.controllers.dto.MoviesSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class OmdbService {

    @Value("${omdb.api.key}")
    String apiKey;
    private final WebClient webClient;

    @Autowired
    public OmdbService(ObjectMapper objectMapper) {
        webClient = WebClient
                .builder()
                .baseUrl("https://omdbapi.com/")
                .build();
    }

    public Movie getMovieById(String id) {
        return webClient
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .queryParam("i", id)
                                .queryParam("apikey", apiKey)
                                .build())
                        .retrieve()
                        .bodyToMono(Movie.class)
                        .block();
    }

    public List<Movie> searchMoviesByTitle(String title) {
        MoviesSearchResponse response = webClient
                        .get()
                        .uri(uriBuilder -> uriBuilder
                                .queryParam("s", title)
                                .queryParam("apikey", apiKey)
                                .build())
                        .retrieve()
                        .bodyToMono(MoviesSearchResponse.class)
                        .block();

        return response.getSearch();
    }

    public record Search(List<Movie> movies) {
    }
}
