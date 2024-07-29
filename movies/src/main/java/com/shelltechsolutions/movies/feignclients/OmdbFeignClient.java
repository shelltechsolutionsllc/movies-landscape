package com.shelltechsolutions.movies.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "https://www.omdbapi.com")
public interface OmdbFeignClient {

    @GetMapping()
    public String getById(@RequestParam String id, @RequestParam String key);
}
