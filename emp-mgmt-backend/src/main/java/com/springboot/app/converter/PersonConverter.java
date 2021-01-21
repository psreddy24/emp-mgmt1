package com.springboot.app.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.springboot.app.model.Person;
import com.springboot.app.model.PersonRequest;

@Component
public class PersonConverter implements Converter<PersonRequest, Person> {
  @Override
  public Person convert(PersonRequest inputRequest) {
    Person person = new Person();
    person.setFirstName(inputRequest.getFirstName());
    person.setLastName(inputRequest.getLastName());
    person.setBirthDate(inputRequest.getBirthDate());
    return person;
  }
}
