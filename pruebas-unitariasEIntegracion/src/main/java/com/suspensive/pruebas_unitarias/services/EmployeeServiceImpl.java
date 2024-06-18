package com.suspensive.pruebas_unitarias.services;

import com.suspensive.pruebas_unitarias.models.Employee;
import com.suspensive.pruebas_unitarias.models.exceptions.ResourceNotFoundException;
import com.suspensive.pruebas_unitarias.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements  IEmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> employeeSaved = employeeRepository.findByEmail(employee.getEmail());
        if (employeeSaved.isPresent()) {
            throw  new ResourceNotFoundException("Employee with email " + employee.getEmail() + " already exists");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Long id,Employee employee) {
        Employee employeeFound = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee with id " + id + " not found"));
        employeeFound.setName(employee.getName());
        employeeFound.setLastname(employee.getLastname());
        employeeFound.setEmail(employee.getEmail());
        return employeeRepository.save(employeeFound);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
