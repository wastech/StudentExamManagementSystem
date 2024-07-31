package com.example.StudentExamManagementSystem.repository;

import com.example.StudentExamManagementSystem.model.Exam;
import com.example.StudentExamManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Long> {


    @Query("SELECT e FROM Exam e WHERE e.user = :user AND e.examDate = :examDate")
    Optional<Exam> findExistingExamForUser(User user, Date examDate);

}
