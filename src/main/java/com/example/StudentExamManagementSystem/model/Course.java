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
//@ToString
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
    private String courseDescription;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Course{" +
            "courseId=" + courseId +
            ", courseName='" + courseName + '\'' +
            ", courseDescription='" + courseDescription + '\'' +
            ", user=" + (user != null ? user.getUserId() : null) +
            '}';
    }
}
