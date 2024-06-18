package com.suspensive.pruebas_unitarias;

import com.suspensive.pruebas_unitarias.models.Employee;

import java.util.List;

public class DataProvider {

    public static List<Employee> employees = List.of(
            new Employee(1L,"Jeferson","Ospina","jefersonospina@gmmail.com"),
            new Employee(2L,"Andres","Sanchez","andres@gmail.com"),
            new Employee(3L,"Simon","Perez","simon@gmail.com")
    );

    public static Employee employeeMock(){
        return new Employee(1L,"Jeferson","Ospina","jefersonospina@gmmail.com");
    }

    public static Employee newEmployee(){
        return new Employee(1L,"Esteban","Cardona","esteban@gmail.com");
    }

}
