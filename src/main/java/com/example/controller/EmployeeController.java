package com.example.controller;

import com.example.dto.EmployeeRequest;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @GetMapping("/display")
    public String displayData(){
        return "Upload Springboot app into AWS EC2 instance";
    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Employee> createEmployee(
            @RequestPart("data") String data,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        EmployeeRequest request = mapper.readValue(data, EmployeeRequest.class);

        return ResponseEntity.ok(employeeService.saveEmployee(request, file));
    }


    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted");
    }
}
