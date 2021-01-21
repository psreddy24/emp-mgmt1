package com.springboot.app.exception;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.springboot.app.constant.EmployeeDataServiceConstant;
import com.springboot.app.model.ErrorResponse;
import com.springboot.app.util.ObjectMapperUtil;

@RestControllerAdvice
public class EmployeeControllerAdvice {

  @ExceptionHandler(NoHandlerFoundException.class)
  public final ResponseEntity<ErrorResponse> handleException(NoHandlerFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(EmployeeDataServiceConstant.ERROR_STATUS);
    errorResponse.setMessage(EmployeeDataServiceConstant.NO_HANDLER_ERROR + ex.getMessage());
    errorResponse.setErrorType(NoHandlerFoundException.class.getSimpleName());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EmployeeServiceException.class)
  public final ResponseEntity<ErrorResponse> handleException(EmployeeServiceException ex) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(EmployeeDataServiceConstant.ERROR_STATUS);
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setErrorType(EmployeeServiceException.class.getSimpleName());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public final ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException ex,
      HttpServletRequest request) {
    Map<String, TreeSet<String>> fieldValidationError = new TreeMap<>();
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    for (FieldError fieldError : fieldErrors) {
      if (fieldValidationError.containsKey(fieldError.getField())) {
        TreeSet<String> error = fieldValidationError.get(fieldError.getField());
        error.add(fieldError.getDefaultMessage());
        fieldValidationError.put(fieldError.getField(), error);
      } else {
        TreeSet<String> error = new TreeSet<>();
        error.add(fieldError.getDefaultMessage());
        fieldValidationError.put(fieldError.getField(), error);
      }
    }
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatus(EmployeeDataServiceConstant.ERROR_STATUS);
    errorResponse.setMessage(EmployeeDataServiceConstant.VALIDATION_ERROR
        + ObjectMapperUtil.returnJsonFromObject(fieldValidationError));
    errorResponse.setErrorType(InputException.class.getSimpleName());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

}
