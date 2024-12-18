package com.coforge.training.agribid.bidder.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coforge.training.agribid.farmer.exception.ResourceNotFound;
import com.coforge.training.agribid.farmer.model.Crop;
import com.coforge.training.agribid.farmer.model.DemoCrop;


@FeignClient(name="farmer-service",url = "http://localhost:8081/agribid/farmer-service")
@Service
public interface CropClient {
	
	//public ResponseEntity<Crop> getCropById(@PathVariable Long cid) throws ResourceNotFound {

	//@GetMapping("/agribid/farmer-service/crops/{cid}")
	@GetMapping("/crops/{cid}")
	public ResponseEntity<Crop> getCropById(@PathVariable Long cid);
		
	    
  //http://localhost:8081/agribid/farmer-service/crops
   // @GetMapping("/agribid/farmer-service/crops")
	   @GetMapping("/crops")
    public List<DemoCrop> getSelectedStates(); 
  

    
    @PutMapping("/crops/{cid}/currentBid")
    public ResponseEntity<Crop> updateCurrentBid(@PathVariable Long cid, @RequestParam double currentBid);
}
      

	
	
	
	




