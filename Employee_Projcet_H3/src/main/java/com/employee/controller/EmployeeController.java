package com.employee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.FileExporter.CsvExporter;
import com.employee.FileExporter.FileExporter;
import com.employee.entity.Employee;
import com.employee.exception.ResourceNotFoundException;
import com.employee.service.IEmployeeService;
import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	@Autowired
	private IEmployeeService service;
	
	@Autowired
	private FileExporter exporter;
	
	@Autowired
	private CsvExporter cexCsvExporter;	
	@GetMapping("/empid/{id}")
	public Employee getEmpById(@PathVariable Integer id) throws ResourceNotFoundException
	{
	
		 Employee emp = service.getEmpBYId(id);
		 if(emp !=null)
		 {
			 return emp;
		 }
		 else {
			 throw new ResourceNotFoundException("employee with this id is not found"+id);
		 }
	}
	
	@GetMapping("/bysalary/{empsalary}")
	public ResponseEntity<List<Employee>>  getEmpBySalary(@PathVariable(name="empsalary") Integer salary)
	{   
		List<Employee> list = service.getEmpBySalary(salary);
		if(list.isEmpty() !=true)
		{
			return ResponseEntity.ok(list);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@GetMapping("/byname")
	public ResponseEntity<List<Employee>> getEmpByName(@RequestParam String Name)
	{
		List<Employee> list = service.getEmpByName(Name);
		if(list.isEmpty() !=true)
		{
			return ResponseEntity.ok(list);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/bycity")
	public ResponseEntity<List<Employee>>  getEmpByCity(@RequestParam (name="c") String city)
	{
		 List<Employee> list = service.getEmpByCity(city);
		 if(list.isEmpty() !=true)
			{
				return ResponseEntity.ok(list);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	
	
	@GetMapping("/15k")
	public  ResponseEntity<List<Employee>> getEmpBySalary15k()
	{
	    List<Employee> list = service.getEmpBySalary();
	    if(list.isEmpty() !=true)
		{
			return ResponseEntity.ok(list);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@GetMapping("/allemp")
	public  ResponseEntity<List<Employee>> getAllEmp(){
		 List<Employee> list = service.getAllEmployee();
		 if(list.isEmpty() !=true)
			{
				return ResponseEntity.ok(list);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	
	//set
	@PostMapping("/save")
	public  ResponseEntity<String> saveEmp(@RequestBody Employee emp)
	{
	
			
			if(emp !=null)
			{
				
				service.save(emp);
				return ResponseEntity.ok("saved succefully");
			}
			
		else
		{
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateEmp(@PathVariable Integer id,@RequestBody Employee emp)
	{
		try {
			service.update(id, emp);
			return ResponseEntity.ok("data is added");
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{id}")
	 public ResponseEntity<String> deleteById(@PathVariable Integer id)
	 {
		Employee employee = service.getEmpBYId(id);
		if(employee !=null)
		{
			service.deleteById(id);
			return ResponseEntity.ok("deleted succcefully");
			
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
	 }
	
	@GetMapping("/getPdf")
	public  ResponseEntity<String> exportTopdf(HttpServletResponse response) throws DocumentException, IOException
	{
		List<Employee> list = service.getAllEmployee();
		if(list.isEmpty() !=true)
		{
			
			exporter.exportToPdf(list, response);
			return ResponseEntity.ok("see the pdf");
			
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}
		
	}

}
