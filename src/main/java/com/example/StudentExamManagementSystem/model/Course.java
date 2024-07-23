package com.example.StudentExamManagementSystem.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Size(max = 100)
    @NotBlank
    @Column(name = "course_name")
    private String courseName;

    @NotBlank
    @Column(name = "course_description", nullable = false)
    private String CourseDescription;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Course(String courseName, String courseDescription) {
        this.courseName = courseName;
        CourseDescription = courseDescription;
    }
}
