package com.example.StudentExamManagementSystem.service;


import com.example.StudentExamManagementSystem.payload.ExamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExamService {

    ExamDTO createExam(ExamDTO examDTO);

    ExamDTO updateExam(Long examId, ExamDTO examDTO);

    void deleteExam(Long examId);

    ExamDTO getExamById(Long examId);

    Page<ExamDTO> getAllExams(Pageable pageable);
}
