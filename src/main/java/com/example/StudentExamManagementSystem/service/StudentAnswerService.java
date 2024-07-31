package com.example.StudentExamManagementSystem.service;

import com.example.StudentExamManagementSystem.payload.StudentAnswerDTO;
import java.util.List;

public interface StudentAnswerService {
    StudentAnswerDTO createStudentAnswer(StudentAnswerDTO studentAnswerDTO);
    StudentAnswerDTO getStudentAnswerById(Long studentAnswerId);
    List<StudentAnswerDTO> getAllStudentAnswers();
    StudentAnswerDTO updateStudentAnswer(Long studentAnswerId, StudentAnswerDTO studentAnswerDTO);
    void deleteStudentAnswer(Long studentAnswerId);
}
