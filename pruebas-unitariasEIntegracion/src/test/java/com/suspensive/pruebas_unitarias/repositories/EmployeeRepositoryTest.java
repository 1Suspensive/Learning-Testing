package com.suspensive.pruebas_unitarias.repositories;

import com.suspensive.pruebas_unitarias.models.Employee;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
//Sirve para probar componentes de la capa repository
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    void setup(){
        this.employee = Employee.builder()
                .name("Jeferson")
                .lastname("Ospina")
                .email("jeferson@gmail.com")
                .build();
    }

    @Test
    public void testSaveEmployee(){
        Employee result = employeeRepository.save(this.employee);

        Assert.assertNotNull(result);
        Assert.assertTrue(result.getId()>0);
    }

    @Test
    public void testFindAllEmployees(){
        employeeRepository.save(this.employee);

        List<Employee> result = employeeRepository.findAll();

        Assert.assertEquals("Jeferson",result.get(0).getName());
        Assert.assertTrue(result.size()>0);

    }

    @Test
    public void testFindEmployeeById(){
        Employee employeeSaved = employeeRepository.save(this.employee);
        Long id = employeeSaved.getId();

        Employee result = employeeRepository.findById(id).orElse(null);

        Assert.assertNotNull(result);
    }

    @Test
    public void testUpdateEmployee(){
        Employee employeeSaved = employeeRepository.save(this.employee);
        Long id = employeeSaved.getId();

        employeeSaved.setName("Andres");

        employeeRepository.save(employeeSaved);

        Employee result = employeeRepository.save(employeeSaved);

        Assert.assertEquals("Andres",result.getName());
    }

    @Test
    public void testDeleteEmployee(){
        Employee employeeSaved = employeeRepository.save(this.employee);
        Long id = employeeSaved.getId();

        employeeRepository.deleteById(id);

        Employee newEmployee = employeeRepository.findById(id).orElse(null);

        Assert.assertNull(newEmployee);


    }
}
