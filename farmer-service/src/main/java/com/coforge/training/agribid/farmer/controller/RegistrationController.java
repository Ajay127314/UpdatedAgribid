package com.coforge.training.agribid.farmer.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coforge.training.agribid.farmer.model.Address;
import com.coforge.training.agribid.farmer.model.Farmer;
import com.coforge.training.agribid.farmer.repository.RegistrationRepository;
import com.coforge.training.agribid.farmer.service.AuthenticationService;
import com.coforge.training.agribid.farmer.service.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;





@RestController
@RequestMapping("/farmer-service")
public class RegistrationController {
	
	@Autowired
	private RegistrationService regService;
 
	@Autowired
	private RegistrationRepository regRepo;
	
	@Autowired
	private AuthenticationService authService;
 
	@Autowired
	private PasswordEncoder passwordEncoder;
 
 
	//DI Using Constructors
		public RegistrationController(RegistrationService regService) {
			super();
			this.regService = regService;
		}
 
	//http://localhost:8081/agribid/farmer-service/registration
 
	//Register Bidder
	@PostMapping("/registration")
	public ResponseEntity<Farmer> registerBidder(@Validated
 
			@RequestPart("farmer") String  farmerJson,
			@RequestPart("aadhaar") MultipartFile aadhaar,
			@RequestPart("pan") MultipartFile pan,
			@RequestPart("certificate") MultipartFile certificate
			
			) throws IOException
	{
 
 
 
		ObjectMapper objectMapper = new ObjectMapper();
		Farmer farmer = objectMapper.readValue(farmerJson, Farmer.class);
 
 
		Address address = farmer.getAddress();
 
		address.setFarmer(farmer);
		farmer.setAddress(address);
		
		
		farmer.setAadhaar(aadhaar.getBytes());
		farmer.setPan(pan.getBytes());
		farmer.setCertificate(certificate.getBytes()); 
 
		Farmer savedBidder = regService.registerFarmer(farmer);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedBidder);		
 
	}
 
 
	//http://localhost:8083/agribid/farmer-service/login
	@PostMapping("/login")
	public ResponseEntity<String> loginBidder(@RequestBody Farmer farmerLogIn) {
		try {
			Farmer farmer = authService.authenticate(farmerLogIn.getEmailId(), farmerLogIn.getPassword());
 
			if (farmer != null) {
				return ResponseEntity.ok("Login successful!");
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
			}
		} catch (Exception e) {
			// Log the exception for debugging purposes
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An Error Occurred: " + e.getMessage());
		}
	}
 
 
	@PostMapping("/update-password")
	public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> request) {
		String emailId = request.get("emailId");
		String newPassword = request.get("newPassword");
		boolean updated = regService.updatePassword(emailId, newPassword);
		if (updated) {
			return ResponseEntity.ok("Password updated successfully!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
		}
	}
 
	
	  @GetMapping("/allfarmers")
	    public List<Farmer> getAllFarmerss() {
	    	return regService.getAllFarmers();
	  }
 

}
