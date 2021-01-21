package com.springboot.app.controller;

import com.springboot.app.converter.EmployeeConverter;
import com.springboot.app.model.Employee;
import com.springboot.app.model.Person;
import com.springboot.app.service.EmployeeService;
import com.springboot.app.util.JsonUtility;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService service;
    @MockBean
    private EmployeeConverter converter;

    @Test
    void createEmployee() throws Exception {
        Person person = new Person(1, "lastname", "firstname", "2020-01-01");
        Employee employee = new Employee(1, person, "1", "2020-01-01", null);
        BDDMockito.given(service.createEmployee(Mockito.any())).willReturn("someId");
        mvc.perform(MockMvcRequestBuilders
                .post("/api/Employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtility.toJson(employee)))
                .andExpect(MockMvcResultMatchers.status().is(200));

        Mockito.verify(service, VerificationModeFactory.times(1))
                .createEmployee(Mockito.any());
        Mockito.reset(service);
    }

    @Test
    void getAllEmployees() throws Exception {
        Person person = new Person(1, "lastname", "firstname", "2020-01-01");
        Employee employee = new Employee(1, person, "1", "2020-01-01", null);
        BDDMockito.given(service.getAllEmployees()).willReturn(Collections.singletonList(employee));
        mvc.perform(MockMvcRequestBuilders
                .get("/api/Employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

        Mockito.verify(service, VerificationModeFactory.times(1))
                .getAllEmployees();
        Mockito.reset(service);
    }

    @Test
    void getEmployeeById() throws Exception {
        Person person = new Person(1, "lastname", "firstname", "2020-01-01");
        Employee employee = new Employee(1, person, "1", "2020-01-01", null);
        BDDMockito.given(service.getEmployeeById(Mockito.any())).willReturn(employee);
        mvc.perform(MockMvcRequestBuilders
                .get("/api/Employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

        Mockito.verify(service, VerificationModeFactory.times(1))
                .getEmployeeById(Mockito.any());
        Mockito.reset(service);
    }

    @Test
    void updateEmployeeById() throws Exception {
        Person person = new Person(1, "lastname", "firstname", "2020-01-01");
        Employee employee = new Employee(1, person, "1", "2020-01-01", null);
        BDDMockito.given(service.updateEmployeeById(1, employee)).willReturn("someId");
        mvc.perform(MockMvcRequestBuilders
                .get("/api/Employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

        Mockito.verify(service, VerificationModeFactory.times(1))
                .getEmployeeById(1);
        Mockito.reset(service);
    }

    @Test
    void deleteEmployee() throws Exception {
        Person person = new Person(1, "lastname", "firstname", "2020-01-01");
        Employee employee = new Employee(1, person, "1", "2020-01-01", null);
        BDDMockito.given(service.deleteEmployeeById(1)).willReturn("someId");
        mvc.perform(MockMvcRequestBuilders
                .get("/api/Employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

        Mockito.verify(service, VerificationModeFactory.times(1))
                .getEmployeeById(1);
        Mockito.reset(service);
    }
}