package com.rajesh.springdata.hibernateinheritance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="card")
@PrimaryKeyJoinColumn(name="id")
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
