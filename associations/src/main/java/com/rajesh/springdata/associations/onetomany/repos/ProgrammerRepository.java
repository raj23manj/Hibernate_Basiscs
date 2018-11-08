package com.rajesh.springdata.associations.onetomany.repos;

import org.springframework.data.repository.CrudRepository;

import com.rajesh.springdata.associations.manytomany.entities.Programmer;

public interface ProgrammerRepository extends CrudRepository<Programmer, Integer> {

}
