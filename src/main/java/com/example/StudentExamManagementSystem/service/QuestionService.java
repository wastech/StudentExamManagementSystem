package com.example.StudentExamManagementSystem.service;


import com.example.StudentExamManagementSystem.payload.QuestionDTO;

import java.util.List;

public interface QuestionService {

    QuestionDTO createQuestion(QuestionDTO questionDTO);

    QuestionDTO getQuestionById(Long questionId);

    List<QuestionDTO> getAllQuestions();

    QuestionDTO updateQuestion(Long questionId, QuestionDTO questionDTO);

    void deleteQuestion(Long questionId);
}
