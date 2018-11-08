package com.rajesh.springdata.componentmapping.repos;

import org.springframework.data.repository.CrudRepository;

import com.rajesh.springdata.componentmapping.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
