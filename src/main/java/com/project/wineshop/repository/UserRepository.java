package com.project.wineshop.repository;

import com.project.wineshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    User findUserByPhoneNumber(String phoneNumber);
}
