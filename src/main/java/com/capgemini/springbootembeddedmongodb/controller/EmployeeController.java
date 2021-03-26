package com.capgemini.springbootembeddedmongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.springbootembeddedmongodb.exception.ErrorMessage;
import com.capgemini.springbootembeddedmongodb.exception.ResourceNotFoundException;
import com.capgemini.springbootembeddedmongodb.model.Employee;
import com.capgemini.springbootembeddedmongodb.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController  {
	
	@Autowired
	private EmployeeRepository repo;

	@GetMapping("/getAll")
	public List<Employee> getAll() {
		
		return repo.findAll();		
	}
	

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return repo.save(employee);
	}
	
	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") int employeeId)
			throws ResourceNotFoundException {

		Employee employee = repo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found by this id " + employeeId));

		return ResponseEntity.ok().body(employee);
	}
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") int employeeId 
			, @RequestBody Employee employeeDetails) throws ResourceNotFoundException{
		
		Employee employee = repo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found by this id " + employeeId));
		
		employee.setName(employeeDetails.getName());
		employee.setAddress(employeeDetails.getAddress());
		repo.save(employee);
		return ResponseEntity.ok().body(employee);
	}
	
	@DeleteMapping("employees/{id}")
	public ResponseEntity<ErrorMessage> deleteEmployee(@PathVariable(value = "id") int employeeId) throws ResourceNotFoundException {
		
		repo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found by this id " + employeeId));
		ErrorMessage message = new  ErrorMessage();
		message.setMessage("Deleted Sucessful");
		repo.deleteById(employeeId);
		
		return ResponseEntity.ok().body(message);
		
	}
	
	
	
	
	
	

}
