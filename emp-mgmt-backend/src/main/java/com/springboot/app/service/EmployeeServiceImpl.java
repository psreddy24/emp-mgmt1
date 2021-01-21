package com.springboot.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;
import com.springboot.app.exception.EmployeeServiceException;
import com.springboot.app.model.Employee;
import com.springboot.app.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

  @Autowired
  private EmployeeRepository employeeRepository;


  @Override
  public String createEmployee(Employee employee) {
    try {
      logger.info("Saving employee details in Repository::");
      employeeRepository.save(employee);
    } catch (QueryTimeoutException e) {
      throw new EmployeeServiceException(e);
    }
    return "Employee details saved successfully.";
  }

  @Override
  public List<Employee> getAllEmployees() {
    List<Employee> employeeList = new ArrayList<>();
    try {
      employeeList = employeeRepository.findAll();
    } catch (QueryTimeoutException e) {
      throw new EmployeeServiceException(e);
    }
    return employeeList;
  }

  @Override
  public Employee getEmployeeById(Integer id) {
    Employee employee = new Employee();
    try {
      Optional<Employee> employeeData = employeeRepository.findById(id);
      if (employeeData.isPresent()) {
        employee = employeeData.get();
      }
    } catch (QueryTimeoutException e) {
      throw new EmployeeServiceException(e);
    }
    return employee;
  }

  @Override
  public String updateEmployeeById(Integer id, Employee employee) {

    try {
      Optional<Employee> employeeData = employeeRepository.findById(id);
      if (employeeData.isPresent()) {
        Employee updateEmployee = employeeData.get();
        updateEmployee.setEmployeeId(employee.getEmployeeId());
        updateEmployee.setEmployeeNum(employee.getEmployeeNum());
        updateEmployee.setPerson(employee.getPerson());
        updateEmployee.setEmployedDate(employee.getEmployedDate());
        updateEmployee.setTerminatedDate(employee.getTerminatedDate());
        logger.info("Updating Employee details in Repository::");
        employeeRepository.save(updateEmployee);
        return "Successfully updated employee: " + id;
      } else {
        return "Employee: " + id + " not found.";
      }
    } catch (QueryTimeoutException e) {
      throw new EmployeeServiceException(e);
    }
  }

  @Override
  public String deleteEmployeeById(Integer id) {
    try {
      employeeRepository.deleteById(id);
    } catch (QueryTimeoutException e) {
      throw new EmployeeServiceException(e);
    }
    return "Successfully deleted employee: " + id;
  }
}
