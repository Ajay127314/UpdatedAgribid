package com.coforge.training.agribid.bidder.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name="bidder_registration")
public class BidderRegistration
{
		

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bid;


	@Column(name = "full_name")
	@NotBlank(message = "Full name is mandatory")
	private String fullName;



	@Column(name = "contact_no", unique = true, nullable = false)
	//@NotBlank(message = "Contact number is mandatory")
	private long contactNo;


	@Column(name = "email_id", nullable = false)
	@NotBlank(message = "Email ID is mandatory")
	@Email(message = "Email should be valid")
	private String email;


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
//
//	@Column(name = "confirm_password")
//	@NotBlank(message = "Confirm password is mandatory")
//	@Size(min = 8, max = 255, message = "Password must contain between 8 and 255 characters")
//	private String confirmPassword;

	@OneToOne(cascade = CascadeType.ALL)
	private BidderAddress address;


	@Lob
	@JsonIgnore
	private byte[] aadhaar;

	@Lob
	@JsonIgnore
	private byte[] pan;


	@Lob
	@JsonIgnore
	private byte[] tradeLicense;

	
	
	
	
}
