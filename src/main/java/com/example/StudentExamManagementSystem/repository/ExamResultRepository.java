package com.example.StudentExamManagementSystem.repository;

import com.example.StudentExamManagementSystem.model.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
    // Custom query methods can be added here if needed
}