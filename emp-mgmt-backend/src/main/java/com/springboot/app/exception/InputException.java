package com.springboot.app.exception;

public class InputException extends Exception {

  private static final long serialVersionUID = 1L;

  public InputException(String message) {
    super(message);
  }

  public InputException(Throwable ex) {
    super(ex);
  }
}
