package com.springboot.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.converter.EmployeeConverter;
import com.springboot.app.model.Employee;
import com.springboot.app.model.EmployeeRequest;
import com.springboot.app.service.EmployeeService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeConverter employeeConverter;

	@PostMapping(value = "/Employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public String createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
		Employee employee = employeeConverter.convert(employeeRequest);
		return employeeService.createEmployee(employee);
	}

	@GetMapping(value = "/Employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employee = null;
		try {
			employee = employeeService.getAllEmployees();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(employee);
		}
		return ResponseEntity.status(HttpStatus.OK).body(employee);

	}

	@GetMapping(value = "/Employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) {
		Employee employee = null;
		try {
			employee = employeeService.getEmployeeById(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(employee);
		}
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	@PutMapping(value = "/Employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateEmployeeById(@PathVariable("id") Integer id,
			@Valid @RequestBody Employee employee) {
		// Employee employee = employeeConverter.convert(employeeRequest);
		return employeeService.updateEmployeeById(id, employee);
	}

	@DeleteMapping(value = "/Employees/{id}")
	public String deleteEmployee(@PathVariable("id") Integer id) {
		return employeeService.deleteEmployeeById(id);
	}
}
