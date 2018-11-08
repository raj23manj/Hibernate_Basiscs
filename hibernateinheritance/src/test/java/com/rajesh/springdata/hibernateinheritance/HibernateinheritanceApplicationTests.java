package com.rajesh.springdata.hibernateinheritance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rajesh.springdata.hibernateinheritance.entities.CreditCard;
import com.rajesh.springdata.hibernateinheritance.entities.Payment;
import com.rajesh.springdata.hibernateinheritance.repos.PaymentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HibernateinheritanceApplicationTests {
	@Autowired
	PaymentRepository repository;

	@Test
	public void testSingleTableInhertance() {
		CreditCard cc = new CreditCard();
		cc.setAmount(1000d);
		cc.setCreditcard("1234-5678-1234");
		
		repository.save(cc);
	}

}
