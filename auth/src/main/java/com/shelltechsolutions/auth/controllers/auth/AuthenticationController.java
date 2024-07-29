package com.shelltechsolutions.auth.controllers.auth;

import com.shelltechsolutions.auth.service.security.AuthenticationService;
import com.shelltechsolutions.auth.controllers.auth.dto.AuthenticationRequest;
import com.shelltechsolutions.auth.controllers.auth.dto.AuthenticationResponse;
import com.shelltechsolutions.auth.controllers.auth.dto.RegisterRequest;
import com.shelltechsolutions.auth.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws Exception {
        AuthenticationResponse response = authService.register(request);

        if (response != null) {
            return ResponseEntity.ok(response);
        }

        throw new Exception("yeah");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/tokenCheck")
    public ResponseEntity<Boolean> tokenCheck() {
        return ResponseEntity.ok(true);
    }

    @GetMapping("/isTokenValid")
    public Token tokenCheck(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return new Token(authService.isTokenValid(authHeader));
    }

    public record Token(Boolean isValid) {
    }
}
