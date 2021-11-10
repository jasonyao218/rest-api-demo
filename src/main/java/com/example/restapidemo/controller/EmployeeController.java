package com.example.restapidemo.controller;

import com.example.restapidemo.model.Employee;
import com.example.restapidemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    // Get all employees
    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        for(Employee e: employeeRepository.findAll()) {
            employees.add(e);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Get employee by id
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
        Optional<Employee> target = employeeRepository.findById(id);
        if(target.isPresent()) {
            return new ResponseEntity<>(target.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Post employee
    @PostMapping("/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeRepository.save(new Employee(employee.getEmployee_firstname(), employee.getEmployee_lastname(), employee.getDepartments())), HttpStatus.OK);
    }

    // Update employee
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
        Optional<Employee> target = employeeRepository.findById(id);
        if(target.isPresent()) {
            Employee target_employee = target.get();
            target_employee.setEmployee_firstname(employee.getEmployee_firstname());
            target_employee.setEmployee_lastname(employee.getEmployee_lastname());
            target_employee.setDepartments(employee.getDepartments());
            return new ResponseEntity<>(employeeRepository.save(target_employee), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // find employee by firstname
    @GetMapping("/employee/{firstname}")
    public ResponseEntity<List<Employee>> findByfirstName(@PathVariable("first") String firstname) {
        List<Employee> result = new ArrayList<>();
        List<Employee> employees = employeeRepository.findEmployeeByFirstname(firstname);
        if(!employees.isEmpty()){
            for(Employee employee: employees) {
                result.add(employee);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // find employee by lastname
    @GetMapping("/employee/{lastname}")
    public ResponseEntity<List<Employee>> findBylastName(@PathVariable("lastname") String lastname) {
        List<Employee> result = new ArrayList<>();
        List<Employee> employees = employeeRepository.findEmployeeByLastname(lastname);
        if (!employees.isEmpty()) {
            for (Employee employee : employees) {
                result.add(employee);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler()
    public ResponseEntity<?> errorResponse() {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
