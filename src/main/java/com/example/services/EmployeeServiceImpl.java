package com.example.services;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	 @Autowired
	    CacheManager cacheManager;
	
	@Autowired
	private KafkaTemplate<String,String> kafka;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Value("${kafka.producer.topic}")
	private String topic;
	

	@Override
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	@Cacheable(value="employeeDetails",key="#root.methodName",unless="#result== null")
	public Optional<Employee> getEmployeeByID(int employeeId) {
		 
		 
		return employeeRepository.findById(employeeId);
	}

	@Override
	public Employee saveEmployeeDetails(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public String deleteEmployeeId(int employeeId) {
		employeeRepository.deleteById(employeeId);
		return "Deleted employee with Id : "+ employeeId;
	}

	@Override
	public Employee updateEmployeeDetails(Employee employee) {
		Employee employeeDetails=employeeRepository.save(employee);
		if (employeeDetails != null) {
			  JSONObject payrollObject = new JSONObject(); 
			  payrollObject.put("employeeId",employee.getEmployeeId());
			  payrollObject.put("salary",employee.getCtc());
			  kafka.send(topic, payrollObject.toString());
		}
		return employeeDetails;
		
	}

}
