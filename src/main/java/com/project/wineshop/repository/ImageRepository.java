package com.project.wineshop.repository;

import com.project.wineshop.model.Image;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByProductId(Long productId);

    void deleteByProductId(Long productId);
}
