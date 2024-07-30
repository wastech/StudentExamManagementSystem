package com.example.StudentExamManagementSystem.service;

import com.example.StudentExamManagementSystem.payload.ExamQuestionDTO;

import java.util.List;

public interface ExamQuestionService {
    ExamQuestionDTO addQuestionToExam(ExamQuestionDTO examQuestionDTO);
    List<ExamQuestionDTO> getQuestionsByExamId(Long examId);
    void removeQuestionFromExam(Long examQuestionId);
}