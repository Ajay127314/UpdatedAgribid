package com.coforge.training.agribid.farmer.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;





@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "farmer_registration")

public class Farmer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
 
	@Column(name = "full_name")
	@NotBlank(message = "Full name is mandatory")
	private String fullName;
	
	
	@Column(name = "contact_no", unique = true, nullable = false)
	@NotNull(message = "Contact number is mandatory")
	private long contactNo;
 
	@Column(name = "email_id", nullable = false)
	@NotBlank(message = "Email ID is mandatory")
	@Email(message = "Email should be valid")
	private String emailId;
 
	@Column(name = "account_no")
	@NotBlank(message = "Account number is mandatory")
	private String accountNo;
 
	@Column(name = "ifsc_code")
	@NotBlank(message = "IFSC code is mandatory")
	private String ifscCode;
 
	@Column(name = "password")
	@NotBlank(message = "Password is mandatory")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;
 
	
 
	@JsonBackReference
	@OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL)
	private List<Crop> crops = new ArrayList<>();
 
  
	@JsonBackReference
	@OneToOne(mappedBy = "farmer", cascade = CascadeType.ALL)
	private Address address;
	
    @Lob
    @JsonIgnore
    private byte[] aadhaar;
    
    @Lob
    @JsonIgnore
    private byte[] pan;
    
    @Lob
    @JsonIgnore
    private byte[] certificate;
  
    @Column(name = "land_area")
    @NotNull(message = "Land area is mandatory")
    private String landArea;
    
    @Column(name = "land_address")
    @NotNull(message = "Land address is mandatory")
    private String landAddress;
    
    @Column(name = "land_pincode")
    @NotNull(message = "Land pincode is mandatory")
    private long landPincode;

}
