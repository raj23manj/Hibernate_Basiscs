package com.rajesh.springdata.transactionmanagement.repos;

import org.springframework.data.repository.CrudRepository;

import com.rajesh.springdata.transactionmanagement.entities.BankAccount;

public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {

}
