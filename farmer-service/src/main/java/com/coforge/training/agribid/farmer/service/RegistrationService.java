package com.coforge.training.agribid.farmer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.coforge.training.agribid.farmer.model.Farmer;
import com.coforge.training.agribid.farmer.repository.CropRepository;
import com.coforge.training.agribid.farmer.repository.RegistrationRepository;


@Service
public class RegistrationService {

	

	@Autowired
	private final RegistrationRepository regRepo ;

	@Autowired
	private final CropRepository cRepo ;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	

	
	public RegistrationService(RegistrationRepository regRepo, CropRepository cRepo, PasswordEncoder passwordEncoder) {
		super();
		this.regRepo = regRepo;
		this.cRepo = cRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public Farmer loginFarmer(String emailId){
		return regRepo.findByEmailId(emailId); //invokes custom method of JPA repo
	}
    
	public Farmer registerFarmer(Farmer f) {
		f.setPassword(passwordEncoder.encode(f.getPassword()));
        return regRepo.save(f);   //invokes pre-defined methods of JPA repo
 
    }
	
	public boolean updatePassword(String emailId, String newPassword) {
        Farmer farmer = regRepo.findByEmailId(emailId);
        if (farmer != null) {
            farmer.setPassword(passwordEncoder.encode(newPassword));
            regRepo.save(farmer);
            return true;
        }
        return false;
    }
	
	public List<Farmer> getAllFarmers() {
        return regRepo.findAll();
    }
 

}
