package com.project.wineshop.repository;

import com.project.wineshop.model.Order;
import com.project.wineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
