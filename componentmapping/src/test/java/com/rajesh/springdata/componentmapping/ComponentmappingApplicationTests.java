package com.rajesh.springdata.componentmapping;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rajesh.springdata.componentmapping.entities.Address;
import com.rajesh.springdata.componentmapping.entities.Employee;
import com.rajesh.springdata.componentmapping.repos.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComponentmappingApplicationTests {
	
	@Autowired
	EmployeeRepository repository;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testCreateEmployee() {
		Employee e = new Employee();
		e.setId(3);
		e.setName("Rajesh");
		Address add = new Address();
		add.setCity("Austin");
		add.setStreetAddress("Street");
		add.setState("state");
		add.setCountry("country");
		add.setZipcode("12312321");
		e.setAddress(add);
		repository.save(e);
	}

}
