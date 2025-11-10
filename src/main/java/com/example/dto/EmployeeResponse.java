package com.example.dto;

import lombok.Data;

@Data
public class EmployeeResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String dob;
    private String department;
    private String imageUrl;
    private String createdAt;
}
