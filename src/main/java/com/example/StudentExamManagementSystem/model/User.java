package com.example.StudentExamManagementSystem.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Table(name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
    })
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank
    @Size(max = 20)
    @Column(name = "firstname")
    private String firstName;

    @NotBlank
    @Size(max = 20)
    @Column(name = "username")
    private String userName;


    @NotBlank
    @Size(max = 20)
    @Column(name = "lastname")
    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name = "email")
    private String email;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private Date dob;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "hire_date")
    private Date hireDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "registration_date")
    private Date registrationDate;


    @NotBlank
    @Size(max = 120)
    @Column(name = "password")
    private String password;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }


    @Setter
    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
        fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @ToString.Exclude
    private List<Course> courses = new ArrayList<>();


    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @ToString.Exclude
    private List<StudentCourse> studentCourses = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Exam> exams = new ArrayList<>();

    public User(String firstName, String userName, String lastName, String email, Date dob, Date hireDate, Date registrationDate, String password) {
        this.firstName = firstName;
        this.userName = userName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.hireDate = hireDate;
        this.registrationDate = registrationDate;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", firstName='" + firstName + '\'' +
            ", userName='" + userName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", dob=" + dob +
            ", hireDate=" + hireDate +
            ", registrationDate=" + registrationDate +
            ", createdAt=" + createdAt +
            '}';
    }

}
