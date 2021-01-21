package com.springboot.app.exception;

public class EmployeeServiceException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public EmployeeServiceException(String message) {
    super(message);
  }

  public EmployeeServiceException(Throwable ex) {
    super(ex);
  }
}
