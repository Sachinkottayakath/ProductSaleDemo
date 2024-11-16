package com.example.productsales.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productsales.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
