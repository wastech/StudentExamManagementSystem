package com.example.StudentExamManagementSystem.controller;

import com.example.StudentExamManagementSystem.payload.ExamResultDTO;
import com.example.StudentExamManagementSystem.service.ExamResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam-results")
public class ExamResultController {

    @Autowired
    private ExamResultService examResultService;

    @PostMapping
    public ResponseEntity<?> createExamResult(@RequestBody ExamResultDTO examResultDTO) {

        try {
            ExamResultDTO createdExamResult = examResultService.createExamResult(examResultDTO);
            System.out.println("createdExamResult"+ createdExamResult);
            return ResponseEntity.ok(createdExamResult);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{examResultId}")
    public ResponseEntity<ExamResultDTO> getExamResultById(@PathVariable Long examResultId) {
        ExamResultDTO examResultDTO = examResultService.getExamResultById(examResultId);
        return ResponseEntity.ok(examResultDTO);
    }

    @GetMapping
    public ResponseEntity<List<ExamResultDTO>> getAllExamResults() {
        List<ExamResultDTO> examResults = examResultService.getAllExamResults();
        return ResponseEntity.ok(examResults);
    }

    @PutMapping("/{examResultId}")
    public ResponseEntity<?> updateExamResult(@PathVariable Long examResultId, @RequestBody ExamResultDTO examResultDTO) {
        try {
            ExamResultDTO updatedExamResult = examResultService.updateExamResult(examResultId, examResultDTO);
            return ResponseEntity.ok(updatedExamResult);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{examResultId}")
    public ResponseEntity<?> deleteExamResult(@PathVariable Long examResultId) {
        try {
            examResultService.deleteExamResult(examResultId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
