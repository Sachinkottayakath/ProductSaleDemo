package com.example.productsales.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productsales.model.Product;
import com.example.productsales.model.Sale;
import com.example.productsales.repository.ProductRepository;
import com.example.productsales.repository.SaleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SaleRepository saleRepository;

	/*public List<Product> getAllProducts() {
		return productRepository.findAll();
	} */
	
	public Page<Product> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

	public Product getProductById(Long id) {
		return productRepository.findById(id)
	            .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
	}

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	public Product updateProduct(Long id, Product product) {
		if (!productRepository.existsById(id)) {
			throw new RuntimeException("Product not found");
		}
		product.setId(id);
		return productRepository.save(product);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	public double getTotalRevenue() {
		List<Sale> sales = saleRepository.findAll();
		return sales.stream().mapToDouble(s -> s.getQuantity() * s.getProduct().getPrice()).sum();
	}

	public double getRevenueByProduct(Long productId) {
		List<Sale> sales = saleRepository.findAll();
		return sales.stream().filter(sale -> sale.getProduct().getId().equals(productId))
				.mapToDouble(sale -> sale.getQuantity() * sale.getProduct().getPrice()).sum();
	}
}
