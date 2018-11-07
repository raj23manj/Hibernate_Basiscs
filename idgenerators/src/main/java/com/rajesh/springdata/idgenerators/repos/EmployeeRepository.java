package com.rajesh.springdata.idgenerators.repos;

import org.springframework.data.repository.CrudRepository;

import com.rajesh.springdata.idgenerators.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
