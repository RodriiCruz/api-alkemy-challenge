package com.challenge.alkemy.api.auth.controller;

import com.challenge.alkemy.api.auth.dto.AuthenticationRequest;
import com.challenge.alkemy.api.auth.dto.AuthenticationResponse;
import com.challenge.alkemy.api.auth.dto.UserDTO;
import com.challenge.alkemy.api.auth.service.UserDetailsCustomService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rodrigo Cruz
 */
@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signUp(@Valid @RequestBody UserDTO dto) {
        userDetailsCustomService.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signIn(@Valid @RequestBody AuthenticationRequest authRequest) throws Exception {
        final String jwt = userDetailsCustomService.signin(authRequest);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
