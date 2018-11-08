package com.rajesh.springdata.associations;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.rajesh.springdata.associations.manytomany.entities.Programmer;
import com.rajesh.springdata.associations.manytomany.entities.Project;
import com.rajesh.springdata.associations.onetomany.entities.Customer;
import com.rajesh.springdata.associations.onetomany.entities.PhoneNumber;
import com.rajesh.springdata.associations.onetomany.repos.CustomerRepository;
import com.rajesh.springdata.associations.onetomany.repos.ProgrammerRepository;
import com.rajesh.springdata.associations.onetoone.entities.License;
import com.rajesh.springdata.associations.onetoone.entities.Person;
import com.rajesh.springdata.associations.onetoone.repos.LicenseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssociationsApplicationTests {
	@Autowired
	CustomerRepository repository;
	
	@Autowired
	ProgrammerRepository pRepository;
	
	@Autowired
	LicenseRepository lRepository;

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
	
	// ManyToMany Associations
	// default load type is lazy like one many
	// same thing applies like one to many
	
	@Test
	public void testManyToMayCreateAssociations() {
		Programmer programmer = new Programmer();
		programmer.setName("Rajesh");
		programmer.setSal(1000000);
		
		HashSet<Project> projects = new HashSet<Project>();
		Project project1 = new Project();
		
		project1.setName("Hiberbate Project");
		projects.add(project1);
		
		programmer.setProjects(projects);
		pRepository.save(programmer);
	}
	
	@Test
	//@Transactional
	public void testManyToMayFindAssociations() {
		Programmer p1 = pRepository.findById(1).get();
		System.out.println(p1);
		System.out.println(p1.getProjects());
	}
	
	// One to One Associations
	@Test
	public void testOnetoOneCreateLicense() {
		License l = new License();
		l.setType("CAR");
		l.setValidFrom(new Date());
		l.setValidTo(new Date());
		
		Person person = new Person();
		person.setAge(27);
		person.setFirstName("Rajesh");
		person.setLastName("Manjunath");

		l.setPerson(person);
		
		lRepository.save(l);
	}
}
