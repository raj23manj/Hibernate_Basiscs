package com.rajesh.springdata.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rajesh.springdata.product.entities.Product;
import com.rajesh.springdata.product.repos.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductdataApplicationTests {
	
	@Autowired
	ProductRepository repository;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testCreate() {
		Product product = new Product();
		product.setId(1);
		product.setName("Iphone");
		product.setDesc("Awesome");
		product.setPrice(1000d);
		
		repository.save(product);
	}
	
	@Test
	public void testRead() {
		int id = 1;
		if(repository.existsById(id)) {
			Optional<Product> findById = repository.findById(id);
			Product product = findById.get();
			assertNotNull(product);
			assertEquals("Iphone", product.getName());
		}
	}
	
	@Test
	public void testCount() {
		System.out.println("Count: " + repository.count()); 
	}
	
	@Test
	public void testUpdate() {
		int id = 1;
		if(repository.existsById(id)) {
			Optional<Product> findById = repository.findById(id);
			Product product = findById.get();
			product.setPrice(1500d);
			repository.save(product);
		}
	}
	
	@Test
	public void testDelete() {
		Optional<Product> findById = repository.findById(1);
		Product product = findById.get();
		repository.delete(product);
	}
	
	@Test
	public void testDeleteExists() {
		int productId = 1;
		if(repository.existsById(productId)) {
			System.out.println("Exists");
			repository.deleteById(productId);
		}
	}
	
	@Test
	public void testFindByName() {
		List<Product> products = repository.findByName("IWatch");
		products.forEach(product -> System.out.println("Product: " + product.getPrice()));	
	}
	
	@Test
	public void testFindByNameAndDesc() {
		List<Product> products = repository.findByNameAndDesc("IWatch", "From Apple Inc");
		products.forEach(product -> System.out.println("Product: " + product.getPrice()));	
	}
	
	@Test
	public void testFindByPrice() {
		List<Product> products = repository.findByPriceGreaterThan(1000d);
		products.forEach(product -> System.out.println("Product: " + product.getPrice()));	
	}
	
	@Test
	public void testFindByDescContains() {
		List<Product> products = repository.findByDescContains("Apple");
		products.forEach(product -> System.out.println("Product: " + product.getPrice()));	
	}
	
	@Test
	public void testFindByPriceBetween() {
		List<Product> products = repository.findByPriceBetween(1000d, 2000d);
		products.forEach(product -> System.out.println("Product: " + product.getPrice()));	
	}
	
	@Test
	public void testFindByDescLike() {
		List<Product> products = repository.findByDescLike("%LG%");
		products.forEach(product -> System.out.println("Product: " + product.getPrice()));	
	}
	
	@Test
	public void testFindByIdIn() {
		List<Product> products = repository.findByIdIn(Arrays.asList(1,2,3));
		products.forEach(product -> System.out.println("Product: " + product.getName()));	
	}

}
