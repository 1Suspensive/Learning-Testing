package com.suspensive.pruebas_unitarias.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suspensive.pruebas_unitarias.DataProvider;
import com.suspensive.pruebas_unitarias.models.Employee;
import com.suspensive.pruebas_unitarias.services.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveEmployee() throws Exception {
        Employee employee = Employee.builder()
                .name("Jeferson")
                .lastname("Ospina")
                .email("jeferson@gmail.com")
                .build();

        when(employeeService.saveEmployee(any(Employee.class)))
                .thenAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Jeferson"))
                .andExpect(jsonPath("$.lastname").value("Ospina"))
                .andExpect(jsonPath("$.email").value("jeferson@gmail.com"));
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(DataProvider.employees);

        ResultActions response = mockMvc.perform(get(""));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(DataProvider.employees.size()));
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Long id = 1L;
        when(employeeService.getEmployeeById(anyLong())).thenReturn(Optional.of(DataProvider.employeeMock()));

        ResultActions response = mockMvc.perform(get("/{id}",id));

        Optional<Employee> optional = Optional.of(DataProvider.employeeMock());

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Jeferson"))
                .andExpect(jsonPath("$.lastname").value("Ospina"))
                .andExpect(jsonPath("$.email").value("jefersonospina@gmmail.com"));

    }

    @Test
    public void testGetEmployeeByIdNotFound() throws Exception {
        Long id = 1L;
        when(employeeService.getEmployeeById(anyLong())).thenReturn(Optional.empty());

        ResultActions response = mockMvc.perform(get("/{id}",id));

        response.andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    public void testUpdateEmployee() throws Exception {
        Long id = 1L;
        when(employeeService.updateEmployee(anyLong(),any(Employee.class))).thenReturn(DataProvider.newEmployee());

        ResultActions response = mockMvc.perform(put("/update/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(DataProvider.newEmployee())));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Esteban"))
                .andExpect(jsonPath("$.lastname").value("Cardona"))
                .andExpect(jsonPath("$.email").value("esteban@gmail.com"));
    }

    @Test
    public void testDeleteemployeeById() throws Exception {
        Long id = 1L;
        doNothing().when(employeeService).deleteEmployee(anyLong());

        ResultActions response = mockMvc.perform(delete("/delete/{id}",id));

        response.andDo(print())
                .andExpect(status().isOk());

    }
}
