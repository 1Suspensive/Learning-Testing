package com.suspensive.pruebas_unitarias.repositories;

import com.suspensive.pruebas_unitarias.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByEmail(String aLong);
}
