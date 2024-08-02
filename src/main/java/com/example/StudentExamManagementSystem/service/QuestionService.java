package com.example.StudentExamManagementSystem.service;


import com.example.StudentExamManagementSystem.payload.ExamDTO;
import com.example.StudentExamManagementSystem.payload.QuestionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionService {

    QuestionDTO createQuestion(QuestionDTO questionDTO);

    QuestionDTO getQuestionById(Long questionId);


    Page<QuestionDTO> getAllQuestions(Pageable pageable);

    QuestionDTO updateQuestion(Long questionId, QuestionDTO questionDTO);

    void deleteQuestion(Long questionId);
}
