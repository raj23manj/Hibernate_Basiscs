package com.rajesh.springdata.product.repos;

import org.springframework.data.repository.CrudRepository;

import com.rajesh.springdata.product.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
