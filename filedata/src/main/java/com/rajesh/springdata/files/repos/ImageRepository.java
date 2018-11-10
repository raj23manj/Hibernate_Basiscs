package com.rajesh.springdata.files.repos;

import org.springframework.data.repository.CrudRepository;

import com.rajesh.springdata.files.entites.Image;

public interface ImageRepository extends CrudRepository<Image, Long> {

}
