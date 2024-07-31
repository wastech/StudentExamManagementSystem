package com.example.StudentExamManagementSystem.repository;

import com.example.StudentExamManagementSystem.model.StudentCourse;
import com.example.StudentExamManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRepository  extends JpaRepository<StudentCourse, Long> {
    boolean existsByUser(User user);
}
