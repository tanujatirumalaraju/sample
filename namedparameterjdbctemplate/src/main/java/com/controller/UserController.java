package com.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.UserModel;
import com.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userservice;

	@PostMapping("/user")
	public Map<String, Object> insertUserDetails(@RequestBody UserModel usermodel) {
		return userservice.insertUserDetails(usermodel);
	}

	@PutMapping("/user/{id}/{email}")
	public Map<String, Object> updateUserDetails(@PathVariable int id, @PathVariable String email) {
		return userservice.updateUserDetails(id, email);
	}

	@PutMapping("/user")
	public Map<String, Object> updateUserDetails(@RequestBody UserModel usermodel) {
		return userservice.updateUserDetails(usermodel);
	}

	@DeleteMapping("/user")
	public Map<String, Object> deleteUserDetails(@RequestBody UserModel usermodel) {
		return userservice.deleteUserDetails(usermodel);
	}

	@GetMapping("/user")
	public Map<String, Object> getUserDetails() {
		return userservice.getUserDetails();
	}
	@GetMapping("/user/{id}")
	public List<Map<String, Object>> getUserDetails(@PathVariable int id) {
		return userservice.getUserDetails(id);
	}

}
