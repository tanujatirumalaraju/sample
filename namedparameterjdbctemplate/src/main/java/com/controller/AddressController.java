package com.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.AddressModel;
import com.model.UserModel;
import com.service.AddressService;
import com.service.UserService;

@RestController
public class AddressController {
	@Autowired
	AddressService addressservice;

	@PostMapping("/address")
	public Map<String, Object> insertAddressDetails(@RequestBody AddressModel addressmodel) {
		return addressservice.insertAddressDetails(addressmodel);
	}

	@PutMapping("/address")
	public Map<String, Object> updateAddressDetails(@RequestBody AddressModel addressmodel) {
		return addressservice.updateAddressDetails(addressmodel);
	}

	@DeleteMapping("/address")
	public Map<String, Object> deleteAddressDetails(@RequestBody AddressModel addressmodele) {
		return addressservice.deleteAddressDetails(addressmodele);
	}

	@GetMapping("/address")
	public Map<String, Object> getAddressDetails() {
		return addressservice.getAddressDetails();
	}
}
