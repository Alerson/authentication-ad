package com.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.model.LdapUser;
import com.authentication.service.Service;

@RestController
@RequestMapping("/api")
public class Controller {

	@Autowired
	Service service;

	@PostMapping("/authenticate/{login}/{password}")
	public ResponseEntity<?> authenticate(@PathVariable("login") String login, @PathVariable("password") String password){
		LdapUser userDetail = service.getUserDetailFromAd(login, password);
		return userDetail != null ? ResponseEntity.ok(userDetail) : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

}
