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
import com.employee.exception.PopException;
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
	
	
	//get
	
	@GetMapping("/empid/{id}")
	public Employee getEmpById(@PathVariable Integer id) throws PopException
	{
	
		 Employee emp = service.getEmpBYId(id);
		 if(emp.getName() !=null)
		 {
			 return emp;
		 }
		 else {
			 throw new PopException("employee with this id is not found");
		 }
	}
	
	
	@GetMapping("/bysalary/{empsalary}")
	public List<Employee> getEmpBySalary(@PathVariable(name="empsalary") Integer salary)
	{
		return service.getEmpBySalary(salary);
	}
	
	
	@GetMapping("/byname")
	public List<Employee> getEmpByName(@RequestParam String Name)
	{
		return service.getEmpByName(Name);
	}
	
	
	@GetMapping("/bycity")
	public List<Employee> getEmpByCity(@RequestParam (name="c") String city)
	{
		return service.getEmpByCity(city);
	}
	
	
	@GetMapping("/15k")
	public List<Employee> getEmpBySalary15k()
	{
		return service.getEmpBySalary();
	}
	
	
	
	@GetMapping("/allemp")
	public List<Employee> getAllEmp(){
		return service.getAllEmployee();
	}
	
	//set
	@PostMapping("/save")
	public String saveEmp(@RequestBody Employee emp)
	{
		if(emp.getName() !=null)
		{
			
			service.save(emp);
			return "data saved";
		}
		else
			return "object is null please check it ";
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateEmp(@PathVariable Integer id,@RequestBody Employee emp)
	{
		try {
			service.update(id, emp);
			return ResponseEntity.ok("data is added");
		} catch (PopException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{id}")
	 public void deleteById(@PathVariable Integer id)
	 {
		service.deleteById(id);
	 }
	
	@GetMapping("/getPdf")
	public void exportTopdf(HttpServletResponse response) throws DocumentException, IOException
	{
		List<Employee> list = service.getAllEmployee();
		exporter.exportToPdf(list, response);
		
	}
	
	@GetMapping("/getCsv")
	public void exportToCsv(HttpServletResponse response) throws DocumentException, IOException
	{
		List<Employee> list = service.getAllEmployee();
		cexCsvExporter.exportToCsv(list, response);
		
	}
	
	

}
