package com.example.StudentExamManagementSystem.repository;

import com.example.StudentExamManagementSystem.model.Exam;
import com.example.StudentExamManagementSystem.model.StudentAnswer;
import com.example.StudentExamManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {
    List<StudentAnswer> findByExamAndUser(Exam exam, User user);

}
