package com.rajesh.springdata.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.Session;
import com.rajesh.springdata.product.entities.Product;
import com.rajesh.springdata.product.repos.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductdataApplicationTests {
	
	@Autowired
	ProductRepository repository;
	
	@Autowired
	EntityManager entityManager; // used by spring repository internally

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
	
	// Paging and Sorting
	
	@Test
	public void testFindAllPaging() {
		 Pageable pageable = new PageRequest(3, 1);
		
		 Page<Product> results = repository.findAll(pageable);
		 results.forEach(product -> System.out.println("Product: " + product.getName()));	
	}
	
	@Test
	public void testFindAllSorting() {
		 Sort sort = new Sort(Direction.DESC, "name");
		 Iterable<Product> results = repository.findAll(sort);
		 results.forEach(product -> System.out.println("Product: " + product.getName()));	
	}
	
	@Test
	public void testFindAllMultipleSorting() {
		 Sort sort = new Sort(Direction.DESC, "name", "price");
		Iterable<Product> results = repository.findAll(sort);
		 results.forEach(product -> System.out.println("Product: " + product.getName() + ":" + product.getPrice()));	
	}
	
	@Test
	public void testFindAllMultipleSortingDirections() {
		 Sort sort = new Sort(new Sort.Order(Direction.DESC, "name"), new Sort.Order(Direction.ASC, "price"));
		Iterable<Product> results = repository.findAll(sort);
		 results.forEach(product -> System.out.println("Product: " + product.getName() + ":" + product.getPrice()));	
	}
	
	@Test
	public void testFindAllSortingDirectionsAndPaging() {
		Pageable pageable = new PageRequest(0, 2, Direction.DESC, "name");
		Page<Product> results = repository.findAll(pageable);
		results.forEach(product -> System.out.println("Product: " + product.getName() + ":" + product.getPrice()));	
	}
	
	@Test
	public void testFindAllMultipleSortingDirectionsAndPaging() {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "name"), new Sort.Order(Direction.ASC, "price"));
		Pageable pageable = new PageRequest(0, 4, sort);
		Page<Product> results = repository.findAll(pageable);
		results.forEach(product -> System.out.println("Product: " + product.getName() + ":" + product.getPrice()));	
	}
	
	@Test
	public void testCustomFinderPaging() {
		Sort sort = new Sort(new Sort.Order(Direction.DESC, "name"), new Sort.Order(Direction.ASC, "price"));
		Pageable pageable = new PageRequest(0, 4, sort);
		
		List<Product> results = repository.findByIdIn(Arrays.asList(1,2,3,4), pageable);		
		results.forEach(product -> System.out.println("Product: " + product.getName() + ":" + product.getPrice()));	
	}
	
	// caching test Level1 each session having its own cache
	@Test
	@Transactional // level 1 caching is enabled, marking on service and tests. 
	public void testCaching() {
		// see console, only once query is fired, not adding @Transactional will fire queries 3 times
//		Product product = repository.findById(1).get();
//		product.setDesc("Caching");
//		repository.save(product);  // need to test this
		
		org.hibernate.Session session = entityManager.unwrap(org.hibernate.Session.class);
		Product product = repository.findById(1).get();
		
		repository.findById(1).get(); // not run query, already in cache
		// for level 2 after setting up cache there will be one query
		session.evict(product); // removing from cache
		repository.findById(1).get(); // will run query
	}
	
	// EH cache, level 2 Cache Provider:
	

}
