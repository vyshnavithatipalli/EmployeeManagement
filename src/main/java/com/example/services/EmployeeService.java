package com.example.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import com.example.model.Employee;

@Component
public interface EmployeeService {

	public CompletableFuture<List<Employee>> getEmployees();

	public Optional<Employee> getEmployeeByID(int employeeId);

	public Employee saveEmployeeDetails(Employee employee);

	public String deleteEmployeeId(int employeeId);

	public Employee updateEmployeeDetails(Employee employee);

}
