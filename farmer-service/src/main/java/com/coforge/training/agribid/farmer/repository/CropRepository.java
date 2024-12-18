package com.coforge.training.agribid.farmer.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.coforge.training.agribid.farmer.model.Crop;
import com.coforge.training.agribid.farmer.model.DemoCrop;



public interface CropRepository extends JpaRepository<Crop,Long > {
	
//	@Query("SELECT new com.coforge.training.agribid.farmer.model.DemoCrop"
//            + "(cid,cropType,cropName,basePrice,currentBid,postedDateTime)"
//            + " FROM Crop")
	
//	@Query("SELECT new com.coforge.training.agribid.farmer.model.DemoCrop"
//            + "(c.cid, c.cropType, c.cropName, c.basePrice, c.currentBid, c.postedDateTime)"
//            + " FROM Crop c")
//    List<DemoCrop> findSelectedCrops();
	/*
	 * DemoCrop(long cid, String cropType, String cropName, Double basePrice, Double currentBid,
				LocalDateTime postedDateTime)
	 */
	@Query("SELECT new com.coforge.training.agribid.farmer.model.DemoCrop "
           + "(c.cid, c.cropType, c.cropName, c.basePrice, c.currentBid, c.postedDateTime)"
           + " FROM Crop c")
    List<DemoCrop> findAllCrops();


}
