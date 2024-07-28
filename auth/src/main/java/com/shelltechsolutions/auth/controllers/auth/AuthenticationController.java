package com.shelltechsolutions.auth.controllers.auth;

import com.shelltechsolutions.auth.service.security.AuthenticationService;
import com.shelltechsolutions.auth.controllers.auth.dto.AuthenticationRequest;
import com.shelltechsolutions.auth.controllers.auth.dto.AuthenticationResponse;
import com.shelltechsolutions.auth.controllers.auth.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws Exception {
        AuthenticationResponse response = service.register(request);

        if (response != null) {
            return ResponseEntity.ok(response);
        }

        throw new Exception("yeah");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/tokenCheck")
    public ResponseEntity<Boolean> tokenCheck() {
        return ResponseEntity.ok(true);
    }
}
