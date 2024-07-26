package com.example.StudentExamManagementSystem.controller;

import com.example.StudentExamManagementSystem.model.User;
import com.example.StudentExamManagementSystem.payload.StudentCourseDTO;
import com.example.StudentExamManagementSystem.service.StudentCourseService;
import com.example.StudentExamManagementSystem.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class StudentCourseController {
    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private StudentCourseService studentCourseService;

    @PostMapping("/student-courses")
    public ResponseEntity<StudentCourseDTO> createStudentCourse(@Valid @RequestBody StudentCourseDTO studentCourseDTO) {
        User user = authUtil.loggedInUser();
        studentCourseDTO.setUserId(user.getUserId());
        StudentCourseDTO savedStudentCourseDTO = studentCourseService.createStudentCourse(studentCourseDTO);
        return new ResponseEntity<>(savedStudentCourseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/student-courses")
    public ResponseEntity<List<StudentCourseDTO>> getAllStudentCourses() {
        List<StudentCourseDTO> studentCourseList = studentCourseService.getAllStudentCourses();
        return new ResponseEntity<>(studentCourseList, HttpStatus.OK);
    }

    @GetMapping("/student-courses/{studentCourseId}")
    public ResponseEntity<StudentCourseDTO> getStudentCourseById(@PathVariable Long studentCourseId) {
        StudentCourseDTO studentCourseDTO = studentCourseService.getStudentCourseById(studentCourseId);
        return new ResponseEntity<>(studentCourseDTO, HttpStatus.OK);
    }

    @PutMapping("/student-courses/{studentCourseId}")
    public ResponseEntity<StudentCourseDTO> updateStudentCourse(@PathVariable Long studentCourseId, @RequestBody StudentCourseDTO studentCourseDTO) {
        StudentCourseDTO updatedStudentCourse = studentCourseService.updateStudentCourse(studentCourseId, studentCourseDTO);
        return new ResponseEntity<>(updatedStudentCourse, HttpStatus.OK);
    }

    @DeleteMapping("/student-courses/{studentCourseId}")
    public ResponseEntity<String> deleteStudentCourse(@PathVariable Long studentCourseId) {
        User user = authUtil.loggedInUser();
        String status = studentCourseService.deleteStudentCourse(studentCourseId, user);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
