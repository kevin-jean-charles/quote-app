package com.kcode.quoteapp.controller;

import com.kcode.quoteapp.entity.AuthRequest;
import com.kcode.quoteapp.entity.RegisterRequest;
import com.kcode.quoteapp.service.CustomUserDetailsService;
import com.kcode.quoteapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to Yasss !!";
    }

    @PostMapping("/authenticate")
    public ResponseEntity generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return ResponseEntity.ok( jwtUtil.generateToken(authRequest.getUsername()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody RegisterRequest registerRequest) throws Exception {
        userDetailsService.saveUser(registerRequest);
        return ResponseEntity.ok("user register");
    }
}
