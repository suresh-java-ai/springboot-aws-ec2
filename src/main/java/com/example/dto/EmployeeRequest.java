package com.example.dto;

import lombok.Data;

@Data
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String dob;
    private String department;
}
