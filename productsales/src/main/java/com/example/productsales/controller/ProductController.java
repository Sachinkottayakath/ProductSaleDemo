package com.example.productsales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import com.example.productsales.model.Product;
import com.example.productsales.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	/*
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}*/
	
	@GetMapping
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        return productService.getAllProducts(page, size);
    }

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product = productService.getProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product savedProduct = productService.addProduct(product);
		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		Product updatedProduct = productService.updateProduct(id, product);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/revenue")
	public ResponseEntity<Double> getTotalRevenue() {
		double revenue = productService.getTotalRevenue();
		return new ResponseEntity<>(revenue, HttpStatus.OK);
	}

	@GetMapping("/revenue/{productId}")
	public ResponseEntity<Double> getRevenueByProduct(@PathVariable Long productId) {
		double revenue = productService.getRevenueByProduct(productId);
		return new ResponseEntity<>(revenue, HttpStatus.OK);
	}

}
