package com.springboot.app.model;

public class PersonRequest {

  private String lastName;

  private String firstName;

  private String birthDate;

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public PersonRequest(String lastName, String firstname, String birthDate) {
    super();
    this.lastName = lastName;
    this.firstName = firstname;
    this.birthDate = birthDate;
  }

  public PersonRequest() {
    super();
  }

}
