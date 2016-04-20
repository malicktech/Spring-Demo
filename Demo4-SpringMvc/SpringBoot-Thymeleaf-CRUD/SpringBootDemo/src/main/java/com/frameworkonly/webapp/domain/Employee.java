package com.frameworkonly.webapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="emp_name", length=50, nullable=true)
	@Size(min=3, max=50)
	@NotNull(message = "FempName can not be null ... ")
	private String empName;
	
	@Column(name="emp_designation", length=50, nullable=true)
	@Size(min=3, max=50)
	@NotNull(message = "empDesignation can not be null ... ")
	private String empDesignation;
	
	@Column(name="emp_salary", nullable=true)
	// Size(min=1000)
	@NotNull
	private Float empSalary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public Float getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(Float empSalary) {
		this.empSalary = empSalary;
	}

	
}
