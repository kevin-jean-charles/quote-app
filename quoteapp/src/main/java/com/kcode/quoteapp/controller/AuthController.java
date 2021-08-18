package com.kcode.quoteapp.controller;

import com.kcode.quoteapp.entity.AuthRequest;
import com.kcode.quoteapp.entity.RegisterRequest;
import com.kcode.quoteapp.service.CustomUserDetailsService;
import com.kcode.quoteapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity generateToken(@RequestBody AuthRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        System.out.println(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt =jwtUtil.generateToken(userDetails.getUsername());


        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody RegisterRequest registerRequest) throws Exception {
        userDetailsService.saveUser(registerRequest);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
}
