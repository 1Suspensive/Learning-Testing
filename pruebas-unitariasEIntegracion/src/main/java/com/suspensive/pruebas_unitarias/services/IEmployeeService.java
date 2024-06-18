package com.suspensive.pruebas_unitarias.services;

import com.suspensive.pruebas_unitarias.models.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(Long id);

    Employee updateEmployee(Long id,Employee employee);

    void deleteEmployee(Long id);

}
