package com.example.StudentExamManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_course")
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_course_id")
    private Long studentCourseId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date enrollmentDate;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;


    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
            "studentCourseId=" + studentCourseId +
            ", userId=" + (user != null ? user.getUserId() : "null") +
            ", courseId=" + (course != null ? course.getCourseId() : "null") +
            ", enrollmentDate=" + enrollmentDate +
            ", createdAt=" + createdAt +
            '}';
    }
}
