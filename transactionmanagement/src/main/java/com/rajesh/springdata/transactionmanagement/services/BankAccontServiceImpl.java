package com.rajesh.springdata.transactionmanagement.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rajesh.springdata.transactionmanagement.entities.BankAccount;
import com.rajesh.springdata.transactionmanagement.repos.BankAccountRepository;

@Service
public class BankAccontServiceImpl implements BankAccountService {
	
	@Autowired
	BankAccountRepository repository;

	@Override
	@Transactional // this dose transaction management
	public void transfer(int amount) {
		BankAccount obama = repository.findById(1).get();	
		obama.setBal(obama.getBal() - amount);
		repository.save(obama);
		
		if(true) {
			throw new RuntimeException();
		}
		
		
		BankAccount trump = repository.findById(2).get();		
		trump.setBal(trump.getBal() + amount);
		repository.save(trump);
	}

}
