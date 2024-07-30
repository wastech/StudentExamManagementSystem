package com.example.StudentExamManagementSystem.controller;


import com.example.StudentExamManagementSystem.payload.ExamDTO;
import com.example.StudentExamManagementSystem.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping
    public ResponseEntity<ExamDTO> createExam(@RequestBody ExamDTO examDTO) {
        ExamDTO createdExam = examService.createExam(examDTO);
        return ResponseEntity.ok(createdExam);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> updateExam(@PathVariable Long id, @RequestBody ExamDTO examDTO) {
        ExamDTO updatedExam = examService.updateExam(id, examDTO);
        return ResponseEntity.ok(updatedExam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> getExamById(@PathVariable Long id) {
        ExamDTO examDTO = examService.getExamById(id);
        return ResponseEntity.ok(examDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ExamDTO>> getAllExams(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ExamDTO> examPage = examService.getAllExams(pageable);
        return ResponseEntity.ok(examPage);
    }
}