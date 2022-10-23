package com.employee.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class); 

	
	@Autowired
	private IEmployeeService service;
	
	@Autowired
	private FileExporter exporter;
	
	@Autowired
	private CsvExporter cexCsvExporter;	
	
	@GetMapping("/empid/{id}")
	public Employee getEmpById(@PathVariable Integer id) throws ResourceNotFoundException
	{
		LOG.info("getEmpById method is called ");
		 Employee emp = service.getEmpBYId(id);
		 if(emp !=null)
		 {
			 LOG.info("getEmpById method is Excecuted ");
			 return emp;
		 }
		 else {
			 LOG.error("Employee with this id not exist");
			 throw new ResourceNotFoundException("employee with this id is not found"+id);
		 }
	}
	
	@GetMapping("/bysalary/{empsalary}")
	public ResponseEntity<List<Employee>>  getEmpBySalary(@PathVariable(name="empsalary") Integer salary)
	{  
		LOG.info("getEmpBySalary method is called "); 
		List<Employee> list = service.getEmpBySalary(salary);
		if(list.isEmpty() !=true)
		{
			LOG.info("getEmpBySalary method is executed "); 

			return ResponseEntity.ok(list);
		}
		else {
			LOG.error("with this salary does not exist");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@GetMapping("/byname")
	public ResponseEntity<List<Employee>> getEmpByName(@RequestParam String Name)
	{
		LOG.info("getEmpByName method is called "); 

		List<Employee> list = service.getEmpByName(Name);
		if(list.isEmpty() !=true)
		{
			LOG.info("getEmpByName method is executed ");
			return ResponseEntity.ok(list);
		}
		else {
			LOG.error("Employee with name is not exist");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/bycity")
	public ResponseEntity<List<Employee>>  getEmpByCity(@RequestParam (name="c") String city)
	{
		LOG.info("getEmpBycity method is called "); 

		 List<Employee> list = service.getEmpByCity(city);
		 if(list.isEmpty() !=true)
			{
				LOG.info("getEmpBycity method is executed "); 

				return ResponseEntity.ok(list);
			}
			else {
				LOG.error("no employee belongs from this city");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	
	
	@GetMapping("/15k")
	public  ResponseEntity<List<Employee>> getEmpBySalary15k()
	{
		LOG.info("getEmpBySalary15K method is called "); 

	    List<Employee> list = service.getEmpBySalary();
	    if(list.isEmpty() !=true)
		{
			LOG.info("getEmpBySalary15k method is executed "); 

			return ResponseEntity.ok(list);
		}
		else {
			LOG.error("no employee has salary 15K");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@GetMapping("/allemp")
	public  ResponseEntity<List<Employee>> getAllEmp(){
		LOG.info("getAllEmp method is called "); 

		 List<Employee> list = service.getAllEmployee();
		 if(list.isEmpty() !=true)
			{
				LOG.info("getAllEmp method is Executed"); 

				return ResponseEntity.ok(list);
			}
			else {
				LOG.error("employee list is empty"); 

				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	
	//set
	@PostMapping("/save")
	public  ResponseEntity<String> saveEmp(@RequestBody Employee emp)
	{
	   LOG.info("save method is called");
		if(emp !=null)
			{
				LOG.info("save method is executed");
				service.save(emp);
				return ResponseEntity.ok("saved succefully");
			}
			
		else
		{
			LOG.error("No content to save");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateEmp(@PathVariable Integer id,@RequestBody Employee emp)
	{
		LOG.info("update emp method is called");
		try {
			service.update(id, emp);
			LOG.info("update method is executed");
			return ResponseEntity.ok("data is added");
		} catch (ResourceNotFoundException e) {
			LOG.error("error in update method");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{id}")
	 public ResponseEntity<String> deleteById(@PathVariable Integer id)
	 {
		LOG.info("deleteById method is called");
		Employee employee = service.getEmpBYId(id);
		if(employee !=null)
		{
			service.deleteById(id);
			LOG.info("deleteById method is executed ");
			return ResponseEntity.ok("deleted succcefully");
			
		}
		else
		{
			LOG.error("with this id  no employee pressent so nothing to delete");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
	 }
	
	@GetMapping("/getPdf")
	public  ResponseEntity<String> exportTopdf(HttpServletResponse response) throws DocumentException, IOException
	{
		LOG.info("export to pdf method is called");
		List<Employee> list = service.getAllEmployee();
		if(list.isEmpty() !=true)
		{
			
			exporter.exportToPdf(list, response);
			LOG.info("exportToPdf method is executed");
			return ResponseEntity.ok("see the pdf");
			
		}
		else
		{
			LOG.error("no data pressent to add into pdf");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}
		
	}

}
