package com.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.constants.ApiConstants;
import com.authentication.model.LdapUser;
import com.authentication.service.Service;

@CrossOrigin
@RestController
@RequestMapping(path = ApiConstants.BASE_URL)
public class Controller {

	@Autowired
	Service service;

	@PostMapping("/authenticate/{email}/{password}")
	public ResponseEntity<?> loginUser(@PathVariable("email") String email, @PathVariable("password") String password){
		LdapUser userDetail = service.getUserDetailFromAd(email, password);
		return userDetail != null ? ResponseEntity.ok(userDetail) : new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

}
