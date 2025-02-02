package com.excelr.JwtMorning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excelr.JwtMorning.model.AuthenticationRequest;
import com.excelr.JwtMorning.model.AuthenticationResponse;
import com.excelr.JwtMorning.security.MyUserDetailsService;
import com.excelr.JwtMorning.util.JwtUtil;
@RestController
public class JwtController {
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	 
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	 @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
     	System.out.println("hellloooo1");

	        try {
	        	System.out.println("hellloooo");

	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
	        }
	        catch (BadCredentialsException e) {
	            throw new Exception("Incorrect username or password", e);
	        }

	        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

	        final String jwt = jwtTokenUtil.generateToken(userDetails);

	        return ResponseEntity.ok(new AuthenticationResponse(jwt));
	    }
	 
	 
	 @RequestMapping("/adddata")
	 public String addData() {
		 return "add Data ";
		 
	 }

	
	
	

}
