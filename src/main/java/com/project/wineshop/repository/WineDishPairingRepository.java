package com.project.wineshop.repository;

import com.project.wineshop.model.WineDishPairing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineDishPairingRepository extends JpaRepository<WineDishPairing, Long> {
}
