package com.project.wineshop.controller;

import com.project.wineshop.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/{productId}/image")
    public ResponseEntity<?> save(@PathVariable Long productId,
                                  @RequestParam("image")MultipartFile file) {
        imageService.save(productId, file);
        return new ResponseEntity<>("Image uploaded successfully:"
                + file.getOriginalFilename(), HttpStatus.CREATED);
    }

    @GetMapping("/{productId}/image")
    public ResponseEntity<?> findImageByProductId(@PathVariable Long productId) {
        byte[] imageData = imageService.findByProductId(productId).getImageData();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @DeleteMapping("/{productId}/image")
    public ResponseEntity<?> deleteImageByProductId(@PathVariable Long productId) {
        imageService.deleteByProductId(productId);
        return ResponseEntity.ok("Image deleted successfully");
    }
}
