package com.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.employee.entity.Employee;
import com.employee.exception.ResourceNotFoundException;
import com.employee.repository.IEmployee;
@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	private final IEmployee employee;
	public EmployeeServiceImpl(IEmployee employee)
	{
		this.employee=employee;
	}
	

	@Override
	public void save(Employee emp) {
		employee.save(emp);
		
		
	}

	@Override
	public void saveAll(List<Employee> empList) {
		employee.saveAll(empList);
		
	}


	
	@Override
	public List<Employee> getAllEmployee() {
		Iterable<Employee> all = employee.findAll();
		List<Employee> elist=new ArrayList<Employee>();
		all.forEach(x -> elist.add(x));
		return elist;
	}


	@Override
	public Employee getEmpBYId(Integer id) {
		Employee emp = employee.findById(id).orElse(null);
		
		return emp;
	}


	@Override
	public void deleteById(Integer id) {
	     employee.deleteById(id);
		
	}


	@Override
	public List<Employee> getEmpByName(String name) {
		// TODO Auto-generated method stub
		return employee.findByName(name);
	}


	@Override
	public List<Employee> getEmpBySalary(Integer salary) {
		// TODO Auto-generated method stub
		return employee.findSalary(salary);
	}


	@Override
	public List<Employee> getEmpBySalary() {
		// TODO Auto-generated method stub
		return employee.findSalary15k();
	}


	@Override
	public List<Employee> getEmpByCity(String city) {
		// TODO Auto-generated method stub
		return employee.findByCity(city);
	}


	@Override
	public void update(Integer id,Employee emp) throws ResourceNotFoundException  {
		
			Employee updateEmployee = employee.findById(id).orElseThrow(() -> new ResourceNotFoundException("object is null"));
			updateEmployee.setName(emp.getName());
			updateEmployee.setCity(emp.getCity());
			updateEmployee.setSalary(emp.getSalary());
			employee.save(updateEmployee);
			
		} 
		
	

}
