package com.springboot.app.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.app.exception.EmployeeServiceException;

public final class ObjectMapperUtil {

  private ObjectMapperUtil() {

  }

  public static String returnJsonFromObject(Object obj) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new EmployeeServiceException(e);
    }
  }
}
