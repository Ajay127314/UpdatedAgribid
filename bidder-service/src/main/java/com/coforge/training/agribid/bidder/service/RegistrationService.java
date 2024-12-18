package com.coforge.training.agribid.bidder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coforge.training.agribid.bidder.model.BidderRegistration;
import com.coforge.training.agribid.bidder.repository.RegistrationRepository;



@Service
public class RegistrationService {
	

	@Autowired
	private RegistrationRepository regRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	

	private CropClient cClient;

	private BidderRegistration  bidder;
	
	
	public BidderRegistration registerBidder(BidderRegistration bidder )
	{
		bidder.setPassword(passwordEncoder.encode(bidder.getPassword()));
		return regRepo.save(bidder);
	}
	
	public boolean updatePassword(String email, String newPassword) {
        BidderRegistration bidder = regRepo.findByEmail(email);
        if (bidder != null) {
            bidder.setPassword(passwordEncoder.encode(newPassword));
            regRepo.save(bidder);
            return true;
        }
        return false;
    }
	

	

}
