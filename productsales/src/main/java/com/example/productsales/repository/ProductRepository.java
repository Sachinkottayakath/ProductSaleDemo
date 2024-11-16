package com.example.productsales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productsales.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
