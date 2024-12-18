package com.coforge.training.agribid.bidder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coforge.training.agribid.bidder.model.BidderRegistration;
import com.coforge.training.agribid.bidder.repository.RegistrationRepository;


@Service
public class AuthenticationService {
	
	@Autowired
	private RegistrationRepository regRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public BidderRegistration authenticate(String email, String password) {
	    BidderRegistration bidder = regRepo.findByEmail(email);
	    if (bidder != null && passwordEncoder.matches(password, bidder.getPassword())) {
	        return bidder;
	    }
	    return null;
	}


}
