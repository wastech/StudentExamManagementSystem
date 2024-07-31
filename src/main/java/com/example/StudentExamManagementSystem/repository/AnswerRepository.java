package com.example.StudentExamManagementSystem.repository;


import com.example.StudentExamManagementSystem.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    // Custom query methods can be added here if needed
}
