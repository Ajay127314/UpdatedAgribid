package com.coforge.training.agribid.farmer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coforge.training.agribid.farmer.model.Farmer;



public interface RegistrationRepository extends JpaRepository<Farmer,Long> {
	
	public Farmer findByEmailId(String emailId);

}
