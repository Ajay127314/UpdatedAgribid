package com.coforge.training.agribid.bidder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coforge.training.agribid.bidder.model.BidderRegistration;

public interface RegistrationRepository extends JpaRepository< BidderRegistration,Long> {
	
	public BidderRegistration findByEmail(String email);

}
