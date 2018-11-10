package com.rajesh.springdata.transactionmanagement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rajesh.springdata.transactionmanagement.services.BankAccontServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionmanagementApplicationTests {

	@Autowired
	BankAccontServiceImpl service;
	
	@Test
	public void contextLoads() {
	}
	
	@Test 
	public void testTransaction() {
		service.transfer(500);
	}

}
