package com.springboot.app.service;

import java.util.List;
import com.springboot.app.model.Person;

public interface PersonService {
  
  public String createPerson(Person person);
  
  public List<Person> getAllPersons();
  
  public Person getPersonById(Integer id);
  
  public String updatePersonById(Integer id, Person person);
  
  public String deletePersonById(Integer id);

}
