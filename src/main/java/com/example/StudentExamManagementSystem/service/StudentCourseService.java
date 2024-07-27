package com.example.StudentExamManagementSystem.service;

import com.example.StudentExamManagementSystem.model.User;
import com.example.StudentExamManagementSystem.payload.StudentCourseDTO;

import java.util.List;

public interface StudentCourseService {
    StudentCourseDTO createStudentCourse(StudentCourseDTO studentCourseDTO , User user);
    StudentCourseDTO getStudentCourseById(Long studentCourseId);
    List<StudentCourseDTO> getAllStudentCourses();
    StudentCourseDTO updateStudentCourse(Long studentCourseId, StudentCourseDTO studentCourseDTO);
    String deleteStudentCourse(Long studentCourseId, User user);

}
