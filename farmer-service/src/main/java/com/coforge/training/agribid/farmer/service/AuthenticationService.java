package com.coforge.training.agribid.farmer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coforge.training.agribid.farmer.model.Farmer;
import com.coforge.training.agribid.farmer.repository.RegistrationRepository;


@Service
public class AuthenticationService {
	
	@Autowired
	private RegistrationRepository frepo;
 
	@Autowired
	private PasswordEncoder passwordEncoder;
 
	public Farmer authenticate(String emailId, String password) {
	    Farmer farmer = frepo.findByEmailId(emailId);
	    if (farmer != null && passwordEncoder.matches(password, farmer.getPassword())) {
	        return farmer;
	    }
	    return null;
	}

}
