package com.idat.SISCOFAR.controller;

import com.idat.SISCOFAR.dto.AuthResponse;
import com.idat.SISCOFAR.dto.LoginRequest;
import com.idat.SISCOFAR.security.JwtUtilService;
import com.idat.SISCOFAR.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailServiceImpl userDetailService;

	@Autowired
	private JwtUtilService jwtUtilService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		try {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(401).body("Credenciales incorrectas");
		}

		final UserDetails userDetails = userDetailService.loadUserByUsername(loginRequest.username());

		final String token = jwtUtilService.generateToken(userDetails);

		return ResponseEntity.ok(new AuthResponse(token));
	}
}