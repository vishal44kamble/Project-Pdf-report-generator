package com.employee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Employee_Project")
public class Employee {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String name;
	@Column
	private String city;
	@Column
	private Integer salary;
	
	
	public Employee()
	{
		
	}
	
	
	
	public Employee(String name, String city, Integer salary) {
		super();
		this.name = name;
		this.city = city;
		this.salary = salary;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public Integer getSalary() {
		return salary;
	}



	public void setSalary(Integer salary) {
		this.salary = salary;
	}



	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", city=" + city + ", salary=" + salary + "]";
	}
	
	
	
	
	

}
