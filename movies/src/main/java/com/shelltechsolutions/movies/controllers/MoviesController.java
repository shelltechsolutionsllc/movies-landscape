package com.shelltechsolutions.movies.controllers;

import com.shelltechsolutions.movies.controllers.dto.Movie;
import com.shelltechsolutions.movies.services.OmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private OmdbService service;

    @GetMapping("{id}")
    public Movie getById(@PathVariable String id) {
        return service.getMovieById(id);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Movie> SearchByTitle(@RequestParam String title) {
        return service.searchMoviesByTitle(title);
    }
}
