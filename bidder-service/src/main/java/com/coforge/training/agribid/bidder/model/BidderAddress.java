package com.coforge.training.agribid.bidder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BidderAddress {
	
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aid;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String pincode;
	
	
	@JsonBackReference
	@OneToOne						//One-One Mapping
	@JoinColumn(name="bidder_id")   //ForeignKey Field
	private BidderRegistration bidder;			//Reference Class Object(Dealer)

	
	

	
	
	





}
