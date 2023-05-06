package com.project.wineshop.repository;

import com.project.wineshop.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    Dish findByName(String name);
}
