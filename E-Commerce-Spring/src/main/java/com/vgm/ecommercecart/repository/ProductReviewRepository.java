package com.vgm.ecommercecart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vgm.ecommercecart.entity.ProductReview;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

}
