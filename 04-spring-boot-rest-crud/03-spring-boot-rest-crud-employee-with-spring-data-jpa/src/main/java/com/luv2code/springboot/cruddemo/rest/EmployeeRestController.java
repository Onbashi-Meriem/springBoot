package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class EmployeeRestController {
    // quick and dirty: inject directly employee dao
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        this.employeeService = theEmployeeService;
    }

    // expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.findAll();
    }

    // add mapping for GET "/employees/{employeeId}"
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
        Employee theEmployee= employeeService.findById(employeeId);
        if(theEmployee==null){
            throw new RuntimeException("Employee id not found - "+employeeId);
        }
        return theEmployee;
    }

    // add mapping for POST "/employees" -add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody  Employee newEmployee) {
        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        newEmployee.setId(0);
        Employee dBEmployee= employeeService.save(newEmployee);
        return dBEmployee;
    }

    // add mapping for PUT "/employees" -update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody  Employee newEmployee) {
        Employee dBEmployee= employeeService.save(newEmployee);
        return dBEmployee;
    }


    @DeleteMapping("/employees/{employeeId}")
    public String removeEmployee(@PathVariable int employeeId){
        Employee theEmployee=employeeService.findById(employeeId);
        if(theEmployee==null)
        {
            throw new RuntimeException("Employee id not found - "+employeeId);
        }
        employeeService.delete(employeeId);
        return "Deleted employee id -"+employeeId;
    }
}
