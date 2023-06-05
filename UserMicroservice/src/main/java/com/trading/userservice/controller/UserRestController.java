package com.trading.userservice.controller;

import static java.util.Objects.isNull;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trading.userservice.api.GoogleVerifier;
import com.trading.userservice.pojo.User;
import com.trading.userservice.service.IUserService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserRestController {
	
	@Autowired
	private IUserService service;
	
	public UserRestController() {

	}
	
	@GetMapping("/authorize/{gToken}")
	public ResponseEntity<?> authorize(@PathVariable String gToken) {
		
		String email = null;
		System.out.println(gToken);
		
		email = GoogleVerifier.verifyToken(gToken);
        if (isNull(email)) {
        	System.out.println("Null token");
        	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try {
        	User user = service.validateUser(email);
        	System.out.println("User is " + user);
        	return new ResponseEntity<>(user, HttpStatus.OK);
        }catch(NoResultException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
	}
	
	@PostMapping("/signup/{gToken}")
	public ResponseEntity<?> signUp(@RequestBody User user,@PathVariable String gToken) {
		String email = null;
		System.out.println(gToken);
		
		email = GoogleVerifier.verifyToken(gToken);
        if (isNull(email)) {
        	System.out.println("Null token");
        	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try {
        	User u = service.validateUser(email);
        	System.out.println("Duplicate User" + u);
        	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch(NoResultException e) {
    		service.saveUser(user);
    		return new ResponseEntity<>(user, HttpStatus.CREATED);
        }		

	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		String email = user.getEmail();
        try {
        	User u = service.validateUser(email);
        	System.out.println("Duplicate User" + u);
        	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch(NoResultException e) {
    		service.saveUser(user);
    		return new ResponseEntity<>(user, HttpStatus.CREATED);
        }		

	}

}
