package com.example.restapi;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.restapi.model.Product;
import com.example.restapi.repository.ProductRepository;

@SpringBootApplication
public class RestapiApplication implements CommandLineRunner {

	private ProductRepository productRepository;

	@Autowired
	public void productRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		List<String> types = Arrays.asList(new String[] {"TV", "Refrigerator", "Mobile", "AC", "Microwave", "Washing Machine", "Dishwasher"});
		Random r = new Random();
		for (int i = 1; i <= 100; i++) {
			Product testProduct = new Product();
			testProduct.setName("Simple Product " + i);
			testProduct.setDescription("This is a electornic product # " + i);
			testProduct.setType(types.get(r.nextInt(7)));
			testProduct.setCategory("Electronics");
			productRepository.save(testProduct);
		}

	}
}
