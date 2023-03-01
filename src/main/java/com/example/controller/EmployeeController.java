package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.model.Employee;
import com.example.services.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees() {
		return new ResponseEntity<List<Employee>>(employeeService.getEmployees(),HttpStatus.OK);	
	}
	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<Employee> getEmployeeByID(@PathVariable int employeeId) {
		Optional<Employee> employee = employeeService.getEmployeeByID(employeeId);
		if(employee.isPresent())
			return new ResponseEntity<Employee>(employee.get(), HttpStatus.OK);
		else
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("/employees")
	public ResponseEntity<String> createEmployeeDetails(@RequestBody Employee employee) {
		Employee employeeDetails = employeeService.saveEmployeeDetails(employee);
		return new ResponseEntity<String>("Employee created with id: " + employeeDetails.getEmployeeId(), HttpStatus.CREATED);
	}
	
	@PutMapping("/employees")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
	Optional<Employee> employeeInfo = employeeService.getEmployeeByID(employee.getEmployeeId());
	if(!employeeInfo.isPresent())
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	else {
		Employee employeeDetails = employeeService.updateEmployeeDetails(employee);
		return new ResponseEntity<String>("Updated Employee with id : "+ employeeDetails.getEmployeeId(), HttpStatus.OK);
	}
	}
	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId) {
		Optional<Employee> employeeInfo = employeeService.getEmployeeByID(employeeId);
		if(!employeeInfo.isPresent())
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		else 
			employeeService.deleteEmployeeId(employeeId);
			return new ResponseEntity<String>("Deleted Employee with id: "+ employeeId,HttpStatus.OK);
		
	}

}

