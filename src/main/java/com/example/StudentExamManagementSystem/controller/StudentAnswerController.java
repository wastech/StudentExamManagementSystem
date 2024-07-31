package com.example.StudentExamManagementSystem.controller;

import com.example.StudentExamManagementSystem.payload.StudentAnswerDTO;
import com.example.StudentExamManagementSystem.service.StudentAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student-answers")
public class StudentAnswerController {

    @Autowired
    private StudentAnswerService studentAnswerService;

    @PostMapping
    public ResponseEntity<?> createStudentAnswer(@RequestBody StudentAnswerDTO studentAnswerDTO) {
        try {
            StudentAnswerDTO createdStudentAnswer = studentAnswerService.createStudentAnswer(studentAnswerDTO);
            return ResponseEntity.ok(createdStudentAnswer);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{studentAnswerId}")
    public ResponseEntity<StudentAnswerDTO> getStudentAnswerById(@PathVariable Long studentAnswerId) {
        StudentAnswerDTO studentAnswerDTO = studentAnswerService.getStudentAnswerById(studentAnswerId);
        return ResponseEntity.ok(studentAnswerDTO);
    }

    @GetMapping
    public ResponseEntity<List<StudentAnswerDTO>> getAllStudentAnswers() {
        List<StudentAnswerDTO> studentAnswers = studentAnswerService.getAllStudentAnswers();
        return ResponseEntity.ok(studentAnswers);
    }

    @PutMapping("/{studentAnswerId}")
    public ResponseEntity<?> updateStudentAnswer(@PathVariable Long studentAnswerId, @RequestBody StudentAnswerDTO studentAnswerDTO) {
        try {
            StudentAnswerDTO updatedStudentAnswer = studentAnswerService.updateStudentAnswer(studentAnswerId, studentAnswerDTO);
            return ResponseEntity.ok(updatedStudentAnswer);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{studentAnswerId}")
    public ResponseEntity<?> deleteStudentAnswer(@PathVariable Long studentAnswerId) {
        try {
            studentAnswerService.deleteStudentAnswer(studentAnswerId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
