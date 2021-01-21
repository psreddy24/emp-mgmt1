package com.springboot.app.service;

import java.util.List;

import com.springboot.app.model.Employee;

public interface EmployeeService {
  
    public String createEmployee(Employee employee);
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeById(Integer id);
	
	public String updateEmployeeById(Integer id, Employee employee);
	
	public String deleteEmployeeById(Integer id);
}
