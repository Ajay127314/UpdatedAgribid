package com.coforge.training.agribid.farmer.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coforge.training.agribid.farmer.exception.ResourceNotFound;
import com.coforge.training.agribid.farmer.model.Crop;
import com.coforge.training.agribid.farmer.model.DemoCrop;
import com.coforge.training.agribid.farmer.model.Farmer;
import com.coforge.training.agribid.farmer.repository.CropRepository;
import com.coforge.training.agribid.farmer.repository.RegistrationRepository;




@Service
public class CropService {
	
	  @Autowired
	    private CropRepository cRepo;
	    
//	    @Autowired
//	    private CropClient cClient;
	    
	    @Autowired
	    private RegistrationRepository rRepo;
	    
	    public List<DemoCrop> getCropsInfo() {
	        return cRepo.findAllCrops(); //Invokes Custom Query method
	    }
   
	    
	    public List<Crop> getAllCrops() {
        return cRepo.findAll();
	    }
	    
	   
	    
    public Optional <Crop> getCropById(Long cid) throws ResourceNotFound {
	        return cRepo.findById(cid);
	               
    }
    
    
    

//	    public Crop saveCrop(Crop crop) {
//	    	
//	    	
//	    	crop.setPostedDateTime(LocalDateTime.now());
//	        return cRepo.save(crop);
//	    }
	    public Crop saveCrop(Long fId, Crop crop) {
	        Farmer farmer = rRepo.findById(fId)
	                .orElseThrow(() -> new RuntimeException("Farmer not found"));
	        crop.setFarmer(farmer);
	        crop.setPostedDateTime(LocalDateTime.now());
	        return cRepo.save(crop);
	    }
	    

		 
//		 public void updateCurrentBid(Long cropId, double newBid) throws ResourceNotFound {
//		        Crop crop = cRepo.findById(cropId).orElseThrow(() -> new ResourceNotFound("crop not found"));
//		        crop.setCurrentBid(newBid);
//		        cRepo.save(crop);
//		    }
	    
	    
	    
	    public Crop updateCurrentBid(Long cid, double currentBid) throws ResourceNotFound {
	        Crop crop = cRepo.findById(cid)
	                .orElseThrow(() -> new ResourceNotFound("Crop not found for this id: " + cid));
	        
	        crop.setCurrentBid(currentBid);
	        return cRepo.save(crop);
	    }
	    
	    
	    
	    public Crop updateCrop(Crop crop) {
        return cRepo.save(crop);
	    }
		 
		 
		 
		 
		
	}

	

