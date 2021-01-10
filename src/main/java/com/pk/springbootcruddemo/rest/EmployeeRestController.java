package com.pk.springbootcruddemo.rest;

import com.pk.springbootcruddemo.entity.Employee;
import com.pk.springbootcruddemo.service.EmployeeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "Employee Management API", description = "Basic CRUD API for Employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable int id) {
        Employee emp = employeeService.findById(id);

        if (emp == null) {
            throw new RuntimeException("Employee not found with id " + id);
        }

        return emp;
    }

    @PostMapping("/employees")
    public Employee save(@RequestBody Employee employee) {
        employee.setId(0);
        employeeService.save(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee update(@RequestBody Employee employee) {
        employeeService.save(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteById(@PathVariable int id) {
        Employee emp = employeeService.findById(id);

        if (emp == null) {
            throw new RuntimeException("Employee not found with id " + id);
        }

        employeeService.deleteById(id);

        return "Deleted Employee with id " + id;
    }
}
