package com.project.wineshop.repository;

import com.project.wineshop.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByProductId(Long productId);

    boolean deleteByProductId(Long productId);
}
