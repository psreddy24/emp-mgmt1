package com.springboot.app.exception;

public class PersonServiceException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public PersonServiceException(String message) {
    super(message);
  }

  public PersonServiceException(Throwable ex) {
    super(ex);
  }
}
