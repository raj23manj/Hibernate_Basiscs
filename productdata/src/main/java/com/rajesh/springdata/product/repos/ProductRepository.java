package com.rajesh.springdata.product.repos;

import org.springframework.data.domain.Pageable;

//import java.util.Optional;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rajesh.springdata.product.entities.Product;
import java.lang.String;
import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
	List<Product> findByName(String name);
	List<Product> findByNameAndDesc(String name, String desc);
	List<Product> findByPriceGreaterThan(Double price);
	List<Product> findByDescContains(String desc);
	List<Product> findByPriceBetween(Double price1, Double price2);
	List<Product> findByDescLike(String desc);
	List<Product> findByIdIn(List<Integer> ids);
	
	// for paging and sorting
	// for this we can extend from crud repo and pass like this and will work
	List<Product> findByIdIn(List<Integer> ids, Pageable pageable);
}