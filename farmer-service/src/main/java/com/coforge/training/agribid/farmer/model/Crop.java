package com.coforge.training.agribid.farmer.model;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Crop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cid;

	@Column(name = "crop_type", nullable = false)
	@NotBlank(message = "Crop type is mandatory")
	private String cropType;

	@Column(name = "base_price", nullable = false)
	@NotNull(message = "Base Price is mandatory")
	private double basePrice;

	@Column(name = "crop_name", nullable = false)
	@NotBlank(message = "Crop name is mandatory")
	private String cropName;

	@Column(name = "fertilizer_type", nullable = false)
	@NotBlank(message = "Fertilizer type is mandatory")
	private String fertilizerType;

	@Column(name = "quantity", nullable = false)
	@NotNull(message = "Quantity is mandatory")
	@Positive(message = "Quantity must be a positive number")
	private Integer quantity;

    @JsonBackReference
	@ManyToOne  //one-one mapping
	@JoinColumn(name = "f_id") //foreign key field
	private Farmer farmer; //reference class object


	private double currentBid ;


	private LocalDateTime postedDateTime;  







	@Lob
	@JsonIgnore
	private byte[] soilPHCertificate;

	//	  @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL)
	//	  private Bid bid;




	public Crop() {
		super();

	}
	
	

	public Crop(Long cid, @NotBlank(message = "Crop type is mandatory") String cropType,
			@NotNull(message = "Base Price is mandatory") double basePrice,
			@NotBlank(message = "Crop name is mandatory") String cropName, double currentBid,
			LocalDateTime postedDateTime) {
		super();
		this.cid = cid;
		this.cropType = cropType;
		this.basePrice = basePrice;
		this.cropName = cropName;
		this.currentBid = currentBid;
		this.postedDateTime = postedDateTime;
	}



	public Crop(Long cid, @NotBlank(message = "Crop type is mandatory") String cropType,
			@NotNull(message = "Base Price is mandatory") double basePrice,
			@NotBlank(message = "Crop name is mandatory") String cropName,
			@NotBlank(message = "Fertilizer type is mandatory") String fertilizerType,
			@NotNull(message = "Quantity is mandatory") @Positive(message = "Quantity must be a positive number") Integer quantity,
			Farmer farmer, double currentBid, LocalDateTime postedDateTime, byte[] soilPHCertificate) {
		super();
		this.cid = cid;
		this.cropType = cropType;
		this.basePrice = basePrice;
		this.cropName = cropName;
		this.fertilizerType = fertilizerType;
		this.quantity = quantity;
		this.farmer = farmer;
		this.currentBid = currentBid;
		this.postedDateTime = postedDateTime;
		this.soilPHCertificate = soilPHCertificate;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getCropType() {
		return cropType;
	}

	public void setCropType(String cropType) {
		this.cropType = cropType;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getFertilizerType() {
		return fertilizerType;
	}

	public void setFertilizerType(String fertilizerType) {
		this.fertilizerType = fertilizerType;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Farmer getFarmer() {
		return farmer;
	}

	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}

	public double getCurrentBid() {
		return currentBid;
	}

	public void setCurrentBid(double currentBid) {
		this.currentBid = currentBid;
	}

	public LocalDateTime getPostedDateTime() {
		return postedDateTime;
	}

	public void setPostedDateTime(LocalDateTime postedDateTime) {
		this.postedDateTime = postedDateTime;
	}

	public byte[] getSoilPHCertificate() {
		return soilPHCertificate;
	}

	public void setSoilPHCertificate(byte[] soilPHCertificate) {
		this.soilPHCertificate = soilPHCertificate;
	}





}
