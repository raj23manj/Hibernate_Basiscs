package com.rajesh.springdata.hibernateinheritance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="card")
//@DiscriminatorValue("cc")
public class CreditCard extends Payment{
	@Column(name="cardnumber")
	private String creditcard;

	public String getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}
}
