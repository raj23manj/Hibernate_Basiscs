package com.rajesh.springdata.associations.onetomany.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	//@OneToMany(mappedBy="customer", cascade=CascadeType.ALL) // this is lazy loading, so in tests need to add @Transactional
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<PhoneNumber> numbers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PhoneNumber> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<PhoneNumber> numbers) {
		this.numbers = numbers;
	}
	
	public void addPhoneNumber(PhoneNumber e) {
		if(e !=null) {
			if(numbers == null) {
				numbers = new ArrayList<>();
			}		
			e.setCustomer(this);
			numbers.add(e);
		}
	}
	
	
}
