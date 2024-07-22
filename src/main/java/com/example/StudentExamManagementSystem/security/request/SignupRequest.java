package com.example.StudentExamManagementSystem.security.request;

import java.util.Date;
import java.util.Set;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class SignupRequest {

    @NotBlank
    @Size(max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String userName;

    @NotBlank
    @Size(max = 20)
    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @DateTimeFormat
    private Date dob;

    @DateTimeFormat
    private Date hireDate;

    @DateTimeFormat
    private Date registrationDate;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
