package com.springboot.app.converter;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Component;
import com.springboot.app.exception.EmployeeServiceException;
import com.springboot.app.model.Employee;
import com.springboot.app.model.EmployeeRequest;
import com.springboot.app.model.Person;
import com.springboot.app.repository.PersonRepository;

@Component
public class EmployeeConverter implements Converter<EmployeeRequest, Employee> {

  @Autowired
  private PersonRepository personRepository;

  @Override
  public Employee convert(EmployeeRequest inputRequest) {
    Employee employee = new Employee();
    employee.setEmployeeNum(inputRequest.getEmployeeNum());
    try {
      Optional<Person> personData = personRepository.findById(inputRequest.getPersonId());
      if (personData.isPresent()) {
        Person person = personData.get();
        employee.setPerson(person);
      }else {
        throw new EmployeeServiceException("Person data not found.");
      }
    } catch (QueryTimeoutException e) {
      throw new EmployeeServiceException(e);
    }
    employee.setEmployedDate(inputRequest.getEmployedDate());
    employee.setTerminatedDate(inputRequest.getTerminatedDate());
    return employee;
  }
}
