package com.vgm.ecommercecart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vgm.ecommercecart.dto.ProductReviewDto;
import com.vgm.ecommercecart.service.ProductService;

@RestController
@RequestMapping("/api/products/reviews")
public class ProductReviewController {
	
	@Autowired
	private ProductService productService;
	public ResponseEntity<?> addReview(@RequestBody ProductReviewDto reviewDto) {
		productService.addReview(reviewDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Review Added");
	}
}
