package com.vgm.ecommercecart.seed;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vgm.ecommercecart.entity.Product;
import com.vgm.ecommercecart.repository.ProductRepository;


@Component
public class ProductSeeder implements CommandLineRunner{
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		if(productRepository.count() == 0) {
			List<Product> demoProducts = List.of(
				    new Product(null, "Apple iPhone 17", 82000.0, "SmartPhone with A19 Chip", "Smartphone", 4.9, "Amazon", 79),
				    new Product(null, "Samsung Galaxy S25", 74999.0, "Flagship Android with Snapdragon 8 Gen 4", "Smartphone", 4.7, "Flipkart", 65),
				    new Product(null, "OnePlus 13 Pro", 69999.0, "120Hz AMOLED Display with Hasselblad Camera", "Smartphone", 4.6, "Amazon", 54),
				    new Product(null, "Sony WH-1000XM6", 29999.0, "Industry-leading Noise Cancelling Headphones", "Audio", 4.8, "Croma", 41),
				    new Product(null, "Dell XPS 15 (2026)", 165000.0, "Intel i9 Laptop with RTX Graphics", "Laptop", 4.5, "Amazon", 22),
				    new Product(null, "MacBook Air M4", 124900.0, "Lightweight Laptop with Apple M4 Chip", "Laptop", 4.9, "Flipkart", 38),
				    new Product(null, "iPad Pro 13 M4", 119900.0, "Ultra Retina XDR Display Tablet", "Tablet", 4.8, "Amazon", 26),
				    new Product(null, "Logitech MX Master 4", 8999.0, "Advanced Wireless Mouse for Productivity", "Accessory", 4.7, "Croma", 73),
				    new Product(null, "Amazon Echo Gen 5", 5499.0, "Smart Speaker with Alexa", "Smart Home", 4.4, "Amazon", 91),
				    new Product(null, "Boat Rockerz 550", 1999.0, "Affordable Wireless Headphones", "Audio", 4.2, "Flipkart", 134),
				    new Product(null, "Canon EOS R10", 88999.0, "Mirrorless Camera for Content Creators", "Camera", 4.6, "Amazon", 17),
				    new Product(null, "HP Smart Tank 790", 21999.0, "All-in-One Wireless Printer", "Printer", 4.3, "Flipkart", 49),
				    new Product(null, "Samsung 55-inch Neo QLED", 124999.0, "4K Smart TV with Quantum Matrix Technology", "Television", 4.7, "Croma", 13),
				    new Product(null, "Asus ROG Strix G18", 189999.0, "Gaming Laptop with RTX 4080", "Gaming Laptop", 4.8, "Amazon", 9),
				    new Product(null, "Redmi Note 14 Pro+", 32999.0, "200MP Camera with Fast Charging", "Smartphone", 4.5, "Flipkart", 112)
				);	
			
			productRepository.saveAll(demoProducts);
			System.out.println("Seeded Demo Products");
		}else {
			System.out.println("Products already seeded! Skipping!");
		}
		
	}
	

}
