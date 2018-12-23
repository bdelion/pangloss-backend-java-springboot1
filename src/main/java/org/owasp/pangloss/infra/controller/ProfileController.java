package org.owasp.pangloss.infra.controller;

import org.owasp.pangloss.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController("profile")
public class ProfileController {

    @GetMapping
    public ResponseEntity<User> profile(Principal principal) {
        return ResponseEntity.ok(new User(principal.getName()));
    }
}
