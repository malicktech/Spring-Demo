package net.webapp.springtest.models;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class Person {
	
	/*
	 * attributes 
	 */
	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	 @GeneratedValue(generator = "generator")
//	@GenericGenerator(name = "generator", strategy = "increment")   
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@NotNull
	private String firstname;
	
	@Size(min = 2, max = 25)
	private String lastname;

	@Column(name = "adresse", nullable = false, length = 100)
	private String adresse;
	
	@Version
    private long version;
	
	@Email
	private String emailAddress;
	
	@Past
	private Date birthday; 
	
	@CreatedDate
	@Column(name = "creation_DateTime", nullable = false)
	private ZonedDateTime creationDateTime;
	
	@LastModifiedDate
	@Column(name = "modification_DateTime", nullable = false)
	private ZonedDateTime modificationDateTime;
//	private Date lastAccessed;
	
	private Boolean status;
	
	private int yearLevel;

	
	//Constructeurs, getters et setters
	
	public Person() {
	}
	/*
	 @PrePersist
	  public void prePersist() {
	      ZonedDateTime now = ZonedDateTime.now();
	      this.creationDateTime = now;
	      this.modificationDateTime = now;
	  }
	     
	  @PreUpdate
	  public void preUpdate() {
	      this.modificationDateTime = ZonedDateTime.now();
	  }
	 */   

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getYearLevel() {
		return yearLevel;
	}

	public void setYearLevel(int yearLevel) {
		this.yearLevel = yearLevel;
	}
	
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public ZonedDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(ZonedDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public ZonedDateTime getModificationDateTime() {
		return modificationDateTime;
	}

	public void setModificationDateTime(ZonedDateTime modificationDateTime) {
		this.modificationDateTime = modificationDateTime;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	

}
