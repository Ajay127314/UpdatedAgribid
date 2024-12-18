package com.coforge.training.agribid.farmer.model;


import java.time.LocalDateTime;



public class DemoCrop {
	
//	 private LocalDate date;       // For date only (e.g., 2024-12-16)
//	    private LocalTime time; //  For time only (e.g., 14:30:00) 
	    
	    private Long cid;
	    private String cropType;
	    private String cropName;
	    private Double basePrice;
	    private Double currentBid;
	    private LocalDateTime postedDateTime;
	    
		public DemoCrop(Long cid, String cropType, String cropName, Double basePrice, Double currentBid,
				LocalDateTime postedDateTime) {
			super();
			this.cid = cid;
			this.cropType = cropType;
			this.cropName = cropName;
			this.basePrice = basePrice;
			this.currentBid = currentBid;
			this.postedDateTime = postedDateTime;
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

		public String getCropName() {
			return cropName;
		}

		public void setCropName(String cropName) {
			this.cropName = cropName;
		}

		public Double getBasePrice() {
			return basePrice;
		}

		public void setBasePrice(Double basePrice) {
			this.basePrice = basePrice;
		}

		public Double getCurrentBid() {
			return currentBid;
		}

		public void setCurrentBid(Double currentBid) {
			this.currentBid = currentBid;
		}

		public LocalDateTime getPostedDateTime() {
			return postedDateTime;
		}

		public void setPostedDateTime(LocalDateTime postedDateTime) {
			this.postedDateTime = postedDateTime;
		}
	    
		
	    

}
