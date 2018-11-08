package com.rajesh.springdata.associations;


import java.util.ArrayList;
import java.util.List;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.rajesh.springdata.associations.onetomany.entities.Customer;
import com.rajesh.springdata.associations.onetomany.entities.PhoneNumber;
import com.rajesh.springdata.associations.onetomany.repos.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssociationsApplicationTests {
	@Autowired
	CustomerRepository repository;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testOneToManyAssociation() {
		Customer cust = new Customer();
		cust.setName("Rajesh");
		
		//List<PhoneNumber> numbers = new ArrayList<>(); // or use HashSet // badway
		PhoneNumber p1 = new PhoneNumber();
		PhoneNumber p2 = new PhoneNumber();
		
		p1.setNumber("123123123");
		p1.setType("Mobile");
		//p1.setCustomer(cust); // badway
		
		p2.setNumber("123123123");
		p2.setType("Landline");
		//p2.setCustomer(cust); // badway
		
		//numbers.add(p1); // badway
		//numbers.add(p2); // badway
		
		//cust.setNumbers(numbers); // badway
		cust.addPhoneNumber(p1);
		cust.addPhoneNumber(p2);
		
		repository.save(cust);
	}
	
	@Test
	@Transactional // since one to many is lazy loaded, in tests need to add this, when FetchType is lazy or default left blank
	public void testLoadCustomer() {
		Customer cust= repository.findById(7L).get();
		System.out.println(cust.getName());
		
		List<PhoneNumber> numbers= cust.getNumbers();
		numbers.forEach(e -> System.out.println( e.getType() +" : "  + e.getNumber()));
		
	}
	
	@Test
	//@Transactional // since one to many is lazy loaded, in tests need to add this, when FetchType is lazy or default left blank
	public void testUpdateCustomer() {
		Customer cust= repository.findById(7L).get();
		cust.setName("Rajesh Manjunath");
		
		List<PhoneNumber> numbers= cust.getNumbers();
		numbers.forEach(e -> e.setType("cellular"));
		
		repository.save(cust);
		
	}
	
	
//	@Test // when setting it to lazy loading need to do @Rollback false, like here
//	@Rollback(false)
//	@Transactional // since one to many is lazy loaded, in tests need to add this, when FetchType is lazy or default left blank
//	public void testDeleteCustomer() {
//		repository.deleteById(8L);
//		
//	}

	@Test
	//@Rollback(false) // not required here if eager loading
	//@Transactional // since one to many is lazy loaded, in tests need to add this, when FetchType is lazy or default left blank
	public void testDeleteCustomer() {
		repository.deleteById(9L);
		
	}
}
