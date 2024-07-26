package com.example.StudentExamManagementSystem.repositories;

import com.example.StudentExamManagementSystem.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRepository  extends JpaRepository<StudentCourse, Long> {
}
