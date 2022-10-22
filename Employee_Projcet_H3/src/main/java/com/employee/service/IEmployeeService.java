package com.employee.service;

import java.util.List;

import com.employee.entity.Employee;
import com.employee.exception.ResourceNotFoundException;

public interface IEmployeeService {//9854529529
	
	//set
	public void save(Employee emp);
	
	public void saveAll(List<Employee> empList);
	
	//get:
	
	public Employee getEmpBYId(Integer id);
	public List<Employee> getAllEmployee();
	public List<Employee> getEmpByName(String name);
	public List<Employee> getEmpBySalary(Integer salary);
	public List<Employee> getEmpBySalary();
	public List<Employee> getEmpByCity(String city);
	
	
	
	//update
	
	public void update(Integer id,Employee emp) throws ResourceNotFoundException;
	
	
	//delete
	public void deleteById(Integer id);

}
