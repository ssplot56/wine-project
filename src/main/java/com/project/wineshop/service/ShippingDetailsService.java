package com.project.wineshop.service;

import com.project.wineshop.model.ShippingDetails;

public interface ShippingDetailsService extends GenericService<ShippingDetails> {
    ShippingDetails findShippingDetailsByRegionAndCityAndDeliveryServiceAndWarehouse(String region,
                                                                                     String city,
                                                                                     String deliveryService,
                                                                                     String warehouse);
}
