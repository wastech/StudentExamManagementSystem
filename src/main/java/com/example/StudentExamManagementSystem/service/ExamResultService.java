package com.example.StudentExamManagementSystem.service;

import com.example.StudentExamManagementSystem.payload.ExamResultDTO;

import java.util.List;

public interface ExamResultService {
    ExamResultDTO createExamResult(ExamResultDTO examResultDTO);
    ExamResultDTO getExamResultById(Long examResultId);
    List<ExamResultDTO> getAllExamResults();
    ExamResultDTO updateExamResult(Long examResultId, ExamResultDTO examResultDTO);
    void deleteExamResult(Long examResultId);
}