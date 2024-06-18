package com.suspensive.pruebas_unitarias.services;

import com.suspensive.pruebas_unitarias.DataProvider;
import com.suspensive.pruebas_unitarias.models.Employee;
import com.suspensive.pruebas_unitarias.repositories.EmployeeRepository;
import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;


    @Test
    public void testSaveEmployee(){
        Employee employee = DataProvider.employeeMock();

        this.employeeService.saveEmployee(employee);

        ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(this.employeeRepository).save(any(Employee.class));
        verify(this.employeeRepository).save(employeeCaptor.capture());
        assertEquals("Jeferson",employeeCaptor.getValue().getName() );
    }

    @Test
    public void testGetAllEmployees(){
        when(this.employeeRepository.findAll()).thenReturn(DataProvider.employees);

        List<Employee> employees = employeeService.getAllEmployees();

        assertNotNull(employees);
        assertFalse(employees.isEmpty());
        assertEquals("Jeferson",employees.get(0).getName() );
        verify(this.employeeRepository).findAll();
    }

    @Test
    public void testGetEmployeeById(){
        Long id = 1L;

        when(this.employeeRepository.findById(anyLong())).thenReturn(Optional.of(DataProvider.employeeMock()));
        Optional<Employee> employee = this.employeeService.getEmployeeById(id);

        verify(this.employeeRepository).findById(anyLong());
        assertFalse(employee.isEmpty());
        assertEquals("Jeferson",employee.get().getName() );
    }

    @Test
    public void testUpdateEmployee(){
        Long id = 1L;

        Employee employeeUpdate = Employee.builder()
                .name("Andres")
                .lastname("Ospina")
                .email("Socorro").build();


        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(DataProvider.employeeMock()));

        employeeService.updateEmployee(id,employeeUpdate);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);

        verify(employeeRepository).findById(anyLong());
        verify(employeeRepository).findById(idCaptor.capture());
    }

    @Test
    public void testDeleteEmployee(){
        Long id = 1L;

        employeeService.deleteEmployee(id);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        verify(employeeRepository).deleteById(anyLong());
        verify(employeeRepository).deleteById(idCaptor.capture());
        assertEquals(1L,idCaptor.getValue().longValue());

    }
}
