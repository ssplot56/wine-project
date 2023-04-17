package com.project.wineshop.service;

import com.project.wineshop.model.ShippingDetails;
import org.springframework.data.jpa.repository.Query;

public interface ShippingDetailsService extends GenericService<ShippingDetails> {
    ShippingDetails findShippingDetailsByRegionAndCityAndDeliveryServiceAndWarehouse(String region,
                                                                                     String city,
                                                                                     String deliveryService,
                                                                                     String warehouse);
}
