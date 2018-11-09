package com.rajesh.springdata.product.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY) // comment this out and see the difference
public class Product implements Serializable{
	
	/**
	 * 
	 * when writing to disk and reading this comes into picture, in-case of overflow to disk. If not memory is enough
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String name;
	@Column(name="description")
	private String desc;
	private Double price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
