package com.example.restapidemo.controller;

import com.example.restapidemo.model.Department;
import com.example.restapidemo.model.Employee;
import com.example.restapidemo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    // get all departments
    @GetMapping("/department")
    public ResponseEntity<List<Department>> getAllDepartment() {
        List<Department> departments = new ArrayList<>();
        for(Department d: departmentRepository.findAll()) {
            departments.add(d);
        }
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // get department by id
    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable long id) {
        Optional<Department> target = departmentRepository.findById(id);
        if(target.isPresent()) {
            return new ResponseEntity<>(target.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // create new department
    @PostMapping("/department")
    public ResponseEntity<Department> createNewDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentRepository.save(new Department(department.getDepartment_name(), department.getEmployees())), HttpStatus.OK);
    }

    // update department
    @PutMapping("/department/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") long id, @RequestBody Department department) {
        Optional<Department> target = departmentRepository.findById(id);
        if(target.isPresent()) {
            Department target_department = target.get();
            target_department.setDepartment_name(department.getDepartment_name());
            target_department.setEmployees(department.getEmployees());
            return new ResponseEntity<>(departmentRepository.save(target_department), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
