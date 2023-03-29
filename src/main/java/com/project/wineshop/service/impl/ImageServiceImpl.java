package com.project.wineshop.service.impl;

import com.project.wineshop.model.Image;
import com.project.wineshop.model.Product;
import com.project.wineshop.repository.ImageRepository;
import com.project.wineshop.repository.ProductRepository;
import com.project.wineshop.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    public ImageServiceImpl(ImageRepository imageRepository, ProductRepository productRepository) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Image save(Long productId, MultipartFile file) {
        try {
            Product product = productRepository.findById(productId).orElseThrow(
                    () -> new RuntimeException("Can't find product with id: " + productId));
            Image image = new Image();
            image.setName(file.getOriginalFilename());
            image.setImageData(file.getBytes());
            image.setProduct(product);
            return imageRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException("Can't save image to DB");
        }
    }

    @Override
    public Image findByProductId(Long productId) {
        productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Can't find product with id: " + productId));
        return imageRepository.findByProductId(productId).orElseThrow(
                () -> new RuntimeException("Can't find image for product with id: " + productId));
    }

    @Override
    public void deleteByProductId(Long productId) {
        imageRepository.deleteByProductId(productId);
    }
}
