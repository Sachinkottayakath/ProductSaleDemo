package com.example.productsales;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.productsales.model.Product;
import com.example.productsales.model.Sale;
import com.example.productsales.repository.ProductRepository;
import com.example.productsales.repository.SaleRepository;
import com.example.productsales.service.ProductService;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class ProductsalesApplication implements CommandLineRunner  {
	 
	@Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;
	 
	public static void main(String[] args) {
		SpringApplication.run(ProductsalesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product1 = new Product("Laptop", "High performance laptop", 1200.0, 10);
        Product product2 = new Product("Smartphone", "Latest model smartphone", 800.0, 20);
       
        Sale sale1 = new Sale(product1, 2, LocalDate.now());
        Sale sale2 = new Sale(product2, 5, LocalDate.now());
        
        saleRepository.save(sale1);
        saleRepository.save(sale2);


        product1.setQuantity(product1.getQuantity() - sale1.getQuantity());
        product2.setQuantity(product2.getQuantity() - sale2.getQuantity());
        productRepository.save(product1);
        productRepository.save(product2);


        System.out.println("Total Revenue: " + productService.getTotalRevenue());
        System.out.println("Revenue for Laptop: " + productService.getRevenueByProduct(product1.getId()));
        System.out.println("Revenue for Smartphone: " + productService.getRevenueByProduct(product2.getId()));
	}
	
	
}
