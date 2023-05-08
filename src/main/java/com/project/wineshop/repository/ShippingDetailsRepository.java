package com.project.wineshop.repository;

import com.project.wineshop.model.ShippingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingDetailsRepository extends JpaRepository<ShippingDetails, Long> {
    @Query("FROM ShippingDetails p WHERE p.region=?1 AND p.city=?2 "
            + "AND p.deliveryService=?3 AND p.warehouse=?4")
    ShippingDetails findShippingDetailsByAllFields(String region, String city,
                                                   String deliveryService,
                                                   String warehouse);
}
