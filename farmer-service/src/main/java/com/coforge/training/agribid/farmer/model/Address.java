package com.coforge.training.agribid.farmer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name="farmer_address")
public class Address {
	
	
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long aid;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String pincode;
	
	@OneToOne  //one-one mapping
	@JoinColumn(name = "f_id")
	private Farmer farmer;


}
