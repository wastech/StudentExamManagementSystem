package com.example.StudentExamManagementSystem.repository;


import com.example.StudentExamManagementSystem.model.Exam;
import com.example.StudentExamManagementSystem.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    boolean existsByQuestionText(String questionText);
    boolean existsByQuestionTextAndQuestionIdNot(String questionText, Long questionId);

    int countByExam(Exam exam);

//    int countByExamId(Long examId);


}