package com.kcode.quote.app.controller;

import com.kcode.quote.app.util.JwtTokenUtil;
import com.kcode.quote.app.model.JwtRequest;
import com.kcode.quote.app.model.JwtResponse;
import com.kcode.quote.app.model.UserDto;
import com.kcode.quote.app.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
@RestController
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;


	/**
	 * create auth token base on request
	 * @param authenticationRequest
	 * @return a JwtResponse token
	 * @throws Exception
	 */
	@PostMapping(value = "/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		//
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		//
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		//generate token base on user details (username and password)
		final String token = jwtTokenUtil.generateToken(userDetails);


		//
		return ResponseEntity.ok(new JwtResponse(token, authenticationRequest.getUsername()));
	}

	/**
	 *
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/register")
	public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	/**
	 *
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	private void authenticate(String username, String password) throws Exception {
		try {
			//
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
