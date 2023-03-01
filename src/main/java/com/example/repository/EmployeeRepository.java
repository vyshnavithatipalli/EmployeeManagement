package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, Integer> {
	

}
