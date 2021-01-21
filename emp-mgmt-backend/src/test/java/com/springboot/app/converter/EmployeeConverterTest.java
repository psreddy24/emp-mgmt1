package com.springboot.app.converter;

import com.springboot.app.exception.EmployeeServiceException;
import com.springboot.app.model.Employee;
import com.springboot.app.model.EmployeeRequest;
import com.springboot.app.model.Person;
import com.springboot.app.repository.PersonRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class EmployeeConverterTest {

    @InjectMocks
    private EmployeeConverter testingObject;
    @Mock
    private PersonRepository repository;

    @Before
    public void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvert_failure() {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setPersonId(1);
        Mockito.when(repository.findById(1)).thenReturn(Optional.ofNullable(null));
        Assertions.assertThrows(EmployeeServiceException.class, () -> testingObject.convert(employeeRequest));
    }

    @Test
    void testConvert_success() {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setPersonId(1);
        Mockito.when(repository.findById(1)).thenReturn(Optional.ofNullable(new Person(1, "lastname", "firstname", "2020-01-01")));
        Employee convert = testingObject.convert(employeeRequest);
        Assertions.assertNotNull(convert);
        Assertions.assertEquals(1, convert.getPerson().getPersonId());
        Assertions.assertEquals("lastname", convert.getPerson().getLastName());
    }
}