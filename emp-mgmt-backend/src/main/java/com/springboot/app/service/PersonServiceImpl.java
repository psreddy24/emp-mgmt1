package com.springboot.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;
import com.springboot.app.exception.PersonServiceException;
import com.springboot.app.model.Person;
import com.springboot.app.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{

  private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

  @Autowired
  private PersonRepository personRepository;


  @Override
  public String createPerson(Person person) {
    try {
      logger.info("Saving person details in Repository::");
      personRepository.save(person);
    } catch (QueryTimeoutException e) {
      throw new PersonServiceException(e);
    }
    return null;
  }

  @Override
  public List<Person> getAllPersons() {
    List<Person> personList = new ArrayList<>();
    try {
      personList = personRepository.findAll();
    } catch (QueryTimeoutException e) {
      throw new PersonServiceException(e);
    }
    return personList;
  }

  @Override
  public Person getPersonById(Integer id) {
    Person person = new Person();
    try {
      Optional<Person> personData = personRepository.findById(id);
      if (personData.isPresent()) {
        person = personData.get();
      }
    } catch (QueryTimeoutException e) {
      throw new PersonServiceException(e);
    }
    return person;
  }

  @Override
  public String updatePersonById(Integer id, Person person) {

    try {
      Optional<Person> personData = personRepository.findById(id);
      if (personData.isPresent()) {
        Person updatePerson = personData.get();
        updatePerson.setPersonId(person.getPersonId());
        updatePerson.setFirstName(person.getFirstName());
        updatePerson.setLastName(person.getLastName());
        updatePerson.setBirthDate(person.getBirthDate());
        logger.info("Updating Person details in Repository::");
        personRepository.save(updatePerson);
        return "Successfully updated person: " + id;
      } else {
        return "Person: "+id+" not found.";
      }
    } catch (QueryTimeoutException e) {
      throw new PersonServiceException(e);
    }
  }

  @Override
  public String deletePersonById(Integer id) {
    try {
      personRepository.deleteById(id);
    } catch (QueryTimeoutException e) {
      throw new PersonServiceException(e);
    }
    return "Successfully deleted person: " + id;
  }


}
