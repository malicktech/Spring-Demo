package net.webapp.ebank.entities;

/*
 * TODO : rename to ClientAccount
 *  TODO add property
 * private String Email;
private String password;


private Date creationdDate;
private boolean status;

 */
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;


@Entity
@Table(name="users")
public class User implements Serializable {
	
	@Id
	private String username;
	private String password;
	private boolean actived;
	
	@ManyToMany
	@JoinTable(name="users_roles")
	private Collection<Role> roles;	
	
	/*
	 @Enumerated(EnumType.STRING)
	private UserStatus status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	  
	@ManyToMany
	@JoinTable(name = "UsersAndRoles", 
	joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id") )
	private List<Role> roles;
	 */
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
	}	
	
}
