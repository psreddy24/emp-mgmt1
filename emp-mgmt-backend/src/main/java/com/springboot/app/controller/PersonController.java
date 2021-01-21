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

import com.springboot.app.converter.PersonConverter;
import com.springboot.app.model.Person;
import com.springboot.app.model.PersonRequest;
import com.springboot.app.service.PersonService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PersonController {

  @Autowired
  private PersonService personService;

  @Autowired
  private PersonConverter personConverter;

  @PostMapping(value = "/Person", produces = MediaType.APPLICATION_JSON_VALUE)
  public String createPerson(@Valid @RequestBody PersonRequest personRequest) {
    Person person = personConverter.convert(personRequest);
    return personService.createPerson(person);
  }

  @GetMapping(value = "/Person", produces = MediaType.APPLICATION_JSON_VALUE)
  @CrossOrigin
  public ResponseEntity<List<Person>> getAllPersons() {
    List<Person> person = null;
    try {
      person = personService.getAllPersons();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(person);
    }
    return ResponseEntity.status(HttpStatus.OK).body(person);

  }

  @GetMapping(value = "/Person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Person> getPersonById(@PathVariable("id") Integer id) {
    Person person = null;
    try {
      person = personService.getPersonById(id);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(person);
    }
    return ResponseEntity.status(HttpStatus.OK).body(person);
  }

  @PutMapping(value = "/Person/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public String updatePersonById(@PathVariable("id") Integer id,
      @Valid @RequestBody Person person) {
    // Person person = personConverter.convert(personRequest);
    return personService.updatePersonById(id, person);
  }

  @DeleteMapping(value = "/Person/{id}")
  public String deletePerson(@PathVariable("id") Integer id) {
    return personService.deletePersonById(id);
  }
}
