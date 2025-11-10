package com.example.service;

import com.example.aws.S3Service;
import com.example.dto.EmployeeRequest;
import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private S3Service s3Service;

    public EmployeeService(EmployeeRepository employeeRepository, S3Service s3Service){
        this.employeeRepository = employeeRepository;
        this.s3Service = s3Service;
    }

    public Employee saveEmployee(EmployeeRequest request, MultipartFile file){
       Employee employee = new Employee();
       employee.setFirstName(request.getFirstName());
       employee.setLastName(request.getLastName());
       employee.setEmail(request.getEmail());
       employee.setDob(request.getDob());
       employee.setDepartment(request.getDepartment());
       employee.setMobile(request.getMobile());

       if(file != null){
           String imageUrl = s3Service.uploadFile(file);
           employee.setImageUrl(imageUrl);
       }

       return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findByDeletedFalse();
    }

    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void deleteEmployee(Long id) {
        Employee emp = getEmployee(id);
        emp.setDeleted(true);
        emp.setDeletedAt(OffsetDateTime.now());
        employeeRepository.save(emp);
    }
}
