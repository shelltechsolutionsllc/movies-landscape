package com.shelltechsolutions.movies.controllers;

import com.shelltechsolutions.movies.services.OmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private OmdbService service;

    @GetMapping("{id}")
    public String getById(@PathVariable String id) {
        return service.getMovieById(id);
    }

    @GetMapping("/search")
    @ResponseBody
    public Object SearchByTitle(@RequestParam String title) {
        return service.searchMoviesByTitle(title);
    }
}
