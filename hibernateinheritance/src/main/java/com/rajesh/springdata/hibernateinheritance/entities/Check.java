package com.rajesh.springdata.hibernateinheritance.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="bankcheck")
//@DiscriminatorValue("ch")
public class Check extends Payment{
	private String checknumber;

	public String getChecknumber() {
		return checknumber;
	}

	public void setChecknumber(String checknumber) {
		this.checknumber = checknumber;
	}
	
}
