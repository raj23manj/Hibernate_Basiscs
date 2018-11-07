package com.rajesh.springdata.jpqlandnativesql;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.rajesh.springdata.jpqlandnativesql.entities.Student;
import com.rajesh.springdata.jpqlandnativesql.repos.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpqlandnativesqlApplicationTests {
	@Autowired
	StudentRepository repository;

	@Test
	public void testStudentCreate() {
		Student student = new Student();
		student.setFirstName("John");
		student.setLastName("Ferguson");
		student.setScore(88);
		
		Student student2 = new Student();
		student2.setFirstName("Bill");
		student2.setLastName("Gates");
		student2.setScore(75);
		
		repository.save(student);
		repository.save(student2);
	}
	
	@Test
	public void testFindAllStudents() {
		System.out.println(repository.findAllStudents());
	}
	
	@Test
	public void testFindAllStudentsPartialData() {
		List<Object[]> students = repository.findAllStudentsPartialData();
		students.forEach(s -> System.out.println("FirstName: " + s[0] +" LastName: " +s[1]));		
	}
	
	@Test
	public void testFindAllStudentsFirstName() {
		System.out.println(repository.findAllStudentsByFirstName("John"));		
	}

	@Test
	public void testFindAllStudentsForScores() {
		System.out.println(repository.findStudentsForGivenScores(70, 80));		
	}
	
	@Test
	@Transactional // only in Junit it rollbacks the delete after delete and tests will passs 
	@Rollback(false) // to be used only in Junit
	public void testDeleteAllStudentsByName() {
		repository.deleteStudentsByFirstName("bill");	
	}
}
