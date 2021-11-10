package com.example.restapidemo.repository;

import com.example.restapidemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("FROM Employee WHERE employee_firstname = ?1")
    List<Employee> findEmployeeByFirstname(String firstname);

    @Query("FROM Employee WHERE employee_lastname = ?1")
    List<Employee> findEmployeeByLastname(String lastname);

}
