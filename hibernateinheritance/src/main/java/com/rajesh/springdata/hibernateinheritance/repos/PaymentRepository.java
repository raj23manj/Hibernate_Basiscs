package com.rajesh.springdata.hibernateinheritance.repos;

import org.springframework.data.repository.CrudRepository;

import com.rajesh.springdata.hibernateinheritance.entities.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
	
}
