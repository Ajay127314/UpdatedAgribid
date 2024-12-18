package com.coforge.training.agribid.bidder.model;

import java.time.LocalDateTime;

public class Bid {
	
	
	private Long cid;
    private String cropType;
    private String cropName;
    private Double basePrice;
    private Double currentBid;
    private LocalDateTime postedDateTime;
    private Double bidAmount;
    
    private BidderRegistration bregistration;
    
    

}
