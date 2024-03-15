package com.vsaas.smartsheet.employeecrud.controllers;

import com.smartsheet.api.SmartsheetException;
import com.vsaas.smartsheet.employeecrud.dtos.Employee;
import com.vsaas.smartsheet.employeecrud.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee/list")
    public ResponseEntity getEmployees() {
        System.out.println("getEmployees is called");
        try {
            List<Employee> employees = employeeService.getEmployees();
            return new ResponseEntity(employees, HttpStatus.OK);
        } catch (SmartsheetException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/employee/create")
    public ResponseEntity createEmployee(@Validated @RequestBody Employee employee) {
        System.out.println("createEmployee is called");
        try {
            List<Employee> createdEmployees = employeeService.createEmployee(employee);
            return new ResponseEntity(createdEmployees, HttpStatus.OK);
        } catch (SmartsheetException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/employee/update")
    public ResponseEntity updateEmployee(@Validated @RequestBody Employee employee) {
        System.out.println("updateEmployee is called");
        try {
            List<Employee> updatedEmployees = employeeService.updateEmployee(employee);
            return new ResponseEntity(updatedEmployees, HttpStatus.OK);
        } catch (SmartsheetException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/employee/delete/{rowId}")
    public ResponseEntity deleteEmployee(@PathVariable("rowId") Long rowId) {
        System.out.println("deleteEmployee is called");
        try {
            employeeService.deleteEmployee(rowId);
            return new ResponseEntity("Deleted Employee Details Successfully", HttpStatus.OK);
        } catch (SmartsheetException e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
