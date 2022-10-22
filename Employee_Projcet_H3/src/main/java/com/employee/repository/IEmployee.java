package com.employee.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public interface IEmployee extends JpaRepository<Employee, Integer>{
   
	public List<Employee> findByName(String name);
	public List<Employee> findByCity(String city);
	public List<Employee> findBySalary(Integer salary);
	@Query(value = "select * from Employee_Project where salary=15000" , nativeQuery = true)
	public List<Employee> findSalary15k();

	@Query(value = "select * from Employee_Project where salary >= :salary",nativeQuery = true)
	public List<Employee> findSalary(@Param("salary") Integer salary);

}
