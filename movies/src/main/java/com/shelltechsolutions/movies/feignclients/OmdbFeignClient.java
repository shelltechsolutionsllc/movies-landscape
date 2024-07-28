package com.shelltechsolutions.movies.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "https://www.omdbapi.com")
public interface OmdbFeignClient {

    @GetMapping("/?i={id}&apikey={key}")
    public String getById(@PathVariable long id, @PathVariable long key);
}
