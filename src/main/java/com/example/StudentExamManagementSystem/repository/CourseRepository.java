package com.example.StudentExamManagementSystem.repository;

import com.example.StudentExamManagementSystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository  extends JpaRepository<Course, Long> {
}
