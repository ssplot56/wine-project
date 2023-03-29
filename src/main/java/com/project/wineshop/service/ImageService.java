package com.project.wineshop.service;

import com.project.wineshop.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image save(Long productId, MultipartFile file);

    Image findByProductId(Long productId);

    void deleteByProductId(Long productId);

}
