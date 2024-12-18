package com.coforge.training.agribid.bidder.controller;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coforge.training.agribid.bidder.model.BidderAddress;
import com.coforge.training.agribid.bidder.model.BidderRegistration;
import com.coforge.training.agribid.bidder.repository.RegistrationRepository;
import com.coforge.training.agribid.bidder.service.AuthenticationService;
import com.coforge.training.agribid.bidder.service.CropClient;
import com.coforge.training.agribid.bidder.service.RegistrationService;
import com.coforge.training.agribid.farmer.exception.ResourceNotFound;
import com.coforge.training.agribid.farmer.model.Crop;
import com.coforge.training.agribid.farmer.model.DemoCrop;
import com.fasterxml.jackson.databind.ObjectMapper;





@RestController
@RequestMapping("/bidder-service")
public class RegistrationController
{

	

	@Autowired
	private RegistrationService  regService;

	@Autowired
	private RegistrationRepository regRepo;

	@Autowired
	private AuthenticationService authService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	private CropClient cropClient;
	
//      @Autowired
//     private CropService cService;


	//DI Using Constructors
	public RegistrationController(RegistrationService regService) {
		super();
		this.regService = regService;
	}


	//http://localhost:8083/agribid/bidder-service/registration

	//Register Bidder
	@PostMapping("/registration")
	public ResponseEntity<BidderRegistration> registerBidder(@Validated


			@RequestPart("bidder") String  bidderJson,

			@RequestPart("aadhaar") MultipartFile aadhaar,
			@RequestPart("pan") MultipartFile pan,
			@RequestPart("tradeLicense") MultipartFile tradeLicense ) throws IOException
	{




		ObjectMapper objectMapper = new ObjectMapper();

		BidderRegistration bidder = objectMapper.readValue(bidderJson, BidderRegistration.class);


		BidderAddress address = bidder.getAddress();

		address.setBidder(bidder);
		bidder.setAddress(address);

		bidder.setAadhaar(aadhaar.getBytes());

		bidder.setPan(pan.getBytes());

		bidder.setTradeLicense(tradeLicense.getBytes());



		BidderRegistration savedBidder = regService.registerBidder(bidder);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedBidder);		

	}


	//http://localhost:8083/agribid/bidder-service/login
	@PostMapping("/login")
	public ResponseEntity<String> loginBidder(@RequestBody BidderRegistration bidderLogIn) {
		try {
			BidderRegistration bidder = authService.authenticate(bidderLogIn.getEmail(), bidderLogIn.getPassword());

			if (bidder != null) {
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



	//http://localhost:8083/agribid/bidder/update-password
	@PostMapping("/update-password")
	public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> request) {
		String email = request.get("email");
		String newPassword = request.get("newPassword");
		boolean updated = regService.updatePassword(email, newPassword);
		if (updated) {
			return ResponseEntity.ok("Password updated successfully!");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
		}
	}
	
	
	//http://localhost:8081/agribid/farmer-service/crops
    @GetMapping("/agribid/farmer-service/crops")
    public List<DemoCrop> getSelectedStates() {
        return cropClient. getSelectedStates();
    }
    
    @PutMapping("/crops/{cid}/currentBid")
    public ResponseEntity<String> updateCurrentBid(@PathVariable Long cid, @RequestParam double currentBid) {
        ResponseEntity<Crop> cropResponse = cropClient.getCropById(cid);
        if (cropResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Crop not found.");
        }

        Crop crop = cropResponse.getBody();
        LocalDateTime postedTime = crop.getPostedDateTime();
        LocalDateTime currentTime = LocalDateTime.now();

        if (Duration.between(postedTime, currentTime).toHours() > 6) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bidding time has expired.");
        }

     /*   cropClient.updateCurrentBid(cid, currentBid);
        return ResponseEntity.ok("Bid Amount Updated Successfully!");*/
        ResponseEntity<Crop> response = cropClient.updateCurrentBid(cid, currentBid);
   	  if (response.getStatusCode() == HttpStatus.NOT_FOUND) { return
   	  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); } 
   	  return ResponseEntity.ok("Bid Amount Updated Successfully!"); 
   	  }
    }



	
	
	
	
	
	

