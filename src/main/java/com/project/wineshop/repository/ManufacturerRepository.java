package com.project.wineshop.repository;

import com.project.wineshop.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    @Query("SELECT m FROM Manufacturer m WHERE m.name = :name")
    Manufacturer findByName(@Param("name") String name);
}

