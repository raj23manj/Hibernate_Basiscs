package com.rajesh.springdata.associations.manytomany.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	
	//@ManyToMany(cascade=CascadeType.ALL)
//	@JoinTable(name="programmers_projects", 
//			   joinColumns=@JoinColumn(name="project_id", referencedColumnName="id"), 
//			   inverseJoinColumns=@JoinColumn(name="programmer_id", referencedColumnName="id"))
	@ManyToMany(mappedBy="projects") // it already defined so no need of above one, even that can done
	private Set<Programmer> programmers;

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

	public Set<Programmer> getProgrammers() {
		return programmers;
	}

	public void setProgrammers(Set<Programmer> programmers) {
		this.programmers = programmers;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + "]";
	}
	

}
