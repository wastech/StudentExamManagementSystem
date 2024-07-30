package com.example.StudentExamManagementSystem.repositories;

import com.example.StudentExamManagementSystem.model.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamQuestionRepository extends JpaRepository<ExamQuestion, Long> {
}