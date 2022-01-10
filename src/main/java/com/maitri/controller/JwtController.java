package com.maitri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.maitri.model.JwtRequest;
import com.maitri.model.JwtResponse;
import com.maitri.service.JwtService;
/*
 * This controller added 'Bearer to JWT token'.
 */
@Controller
@CrossOrigin
public class JwtController {

		@Autowired
		private JwtService jwtService;
		public String jwtToken;
	 	//Calls createJwtToken function and Add 'Bearer' to token.
		@PostMapping("/authenticate")
	    public String createJwtToken(@ModelAttribute("jwt") JwtRequest jwtRequest) throws Exception {
	 		JwtResponse jwtResponse= jwtService.createJwtToken(jwtRequest);
	        jwtToken=jwtResponse.getJwtToken();
	        jwtToken ="Bearer " +jwtResponse.getJwtToken();
	        return "redirect:/readuser";
	    }
	}
