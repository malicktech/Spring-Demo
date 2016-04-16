package net.webapp.ebank.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Role implements Serializable{

	@Id
	private String role;
	private String Description;
	
	/*
	 @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String roleName;
	private String roleDescription;
	
	@ManyToMany(mappedBy = "roles")
	private List<User> users;
	
	 */
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	
}
