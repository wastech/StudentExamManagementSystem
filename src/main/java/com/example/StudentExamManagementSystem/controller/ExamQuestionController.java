package com.example.StudentExamManagementSystem.controller;


import com.example.StudentExamManagementSystem.payload.ExamQuestionDTO;
import com.example.StudentExamManagementSystem.service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam-questions")
public class ExamQuestionController {

    @Autowired
    private ExamQuestionService examQuestionService;

    @PostMapping
    public ResponseEntity<?> addQuestionToExam(@RequestBody ExamQuestionDTO examQuestionDTO) {
        try {
            ExamQuestionDTO createdExamQuestion = examQuestionService.addQuestionToExam(examQuestionDTO);
            return ResponseEntity.ok(createdExamQuestion);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<ExamQuestionDTO>> getQuestionsByExamId(@PathVariable Long examId) {
        List<ExamQuestionDTO> examQuestions = examQuestionService.getQuestionsByExamId(examId);
        return ResponseEntity.ok(examQuestions);
    }

    @DeleteMapping("/{examQuestionId}")
    public ResponseEntity<?> removeQuestionFromExam(@PathVariable Long examQuestionId) {
        try {
            examQuestionService.removeQuestionFromExam(examQuestionId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
