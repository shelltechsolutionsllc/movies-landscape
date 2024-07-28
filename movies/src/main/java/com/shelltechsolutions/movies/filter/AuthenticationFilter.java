package com.shelltechsolutions.movies.filter;

import com.shelltechsolutions.movies.services.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationService service;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if ((authHeader == null || !authHeader.startsWith("Bearer "))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "The token is not valid.");
            return;
        }

        service.isAuthorized(authHeader.substring(7));

        filterChain.doFilter(request, response);
    }
}
