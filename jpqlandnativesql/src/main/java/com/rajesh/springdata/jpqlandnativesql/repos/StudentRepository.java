package com.rajesh.springdata.jpqlandnativesql.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rajesh.springdata.jpqlandnativesql.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
	// @Query means it is only read operations
	@Query("from Student") // equal to select * from
	List<Student> findAllStudents(Pageable pageable);
	
	@Query("select st.firstName, st.lastName from Student st") 
	List<Object[]> findAllStudentsPartialData();
	
	@Query("from Student  where firstName=:fname") 
	List<Student> findAllStudentsByFirstName(@Param("fname") String firstName);
	
	@Query("from Student where score>:min and score<:max")
	List<Student> findStudentsForGivenScores(@Param("min") int min, @Param("max") int max);
	
	@Modifying // from spring data tells spring that it is modifying delete/update/insert
	@Query("delete from Student where firstName=:firstName")
	void deleteStudentsByFirstName(@Param("firstName") String firstName);
	
	// Native Query
	
	@Query(value="select * from student", nativeQuery=true)
	List<Student> findAllStudentsNQ();
	
	@Query(value="select * from student  where fname=:fname", nativeQuery=true) 
	List<Student> findByFirstNameNQ(@Param("fname") String firstName);
	
	@Query(value="select st.fname, st.lname from student st", nativeQuery=true) 
	List<Object[]> findAllStudentsPartialDataNQ();
}

