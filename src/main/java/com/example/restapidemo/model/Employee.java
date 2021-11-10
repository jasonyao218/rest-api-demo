package com.example.restapidemo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstname")
    private String employee_firstname;

    @Column(name = "lastname")
    private String employee_lastname;

    @ManyToMany(mappedBy = "employees")
    Set<Department> departments;

    public Employee() {

    }

    public Employee(String employee_firstname, String employee_lastname, Set<Department> departments) {
        this.employee_firstname = employee_firstname;
        this.employee_lastname = employee_lastname;
        this.departments = departments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public void setEmployee_firstname(String employee_firstname) {
        this.employee_firstname = employee_firstname;
    }

    public String getEmployee_firstname() {
        return employee_firstname;
    }

    public void setEmployee_lastname(String employee_lastname) {
        this.employee_lastname = employee_lastname;
    }

    public String getEmployee_lastname() {
        return employee_lastname;
    }
}
