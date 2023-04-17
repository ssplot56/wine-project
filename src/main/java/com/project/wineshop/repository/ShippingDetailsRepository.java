package com.project.wineshop.repository;

import com.project.wineshop.model.ShippingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingDetailsRepository extends JpaRepository<ShippingDetails, Long> {
    @Query("from ShippingDetails p where p.region=?1 and p.city=?2 and p.deliveryService=?3 and p.warehouse=?4")
    ShippingDetails findShippingDetailsByRegionAndCityAndDeliveryServiceAndWarehouse(String region,
                                                                                     String city,
                                                                                     String deliveryService,
                                                                                     String warehouse);
}
