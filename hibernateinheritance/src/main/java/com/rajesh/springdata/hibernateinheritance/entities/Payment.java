package com.rajesh.springdata.hibernateinheritance.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name="pmode", discriminatorType=DiscriminatorType.STRING) // type column in table
public abstract class Payment {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
