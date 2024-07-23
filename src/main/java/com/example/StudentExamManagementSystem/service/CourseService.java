package com.example.StudentExamManagementSystem.service;


import com.example.StudentExamManagementSystem.model.User;
import com.example.StudentExamManagementSystem.payload.CourseDTO;

import java.util.List;

public interface CourseService {

    CourseDTO createCourse(CourseDTO courseDTO, User user);

    List<CourseDTO> getcourses();

    CourseDTO getCourseById(Long courseId);

    List<CourseDTO> getUserCourses(User user);


    String deleteCourseById(Long courseId);


    CourseDTO updateCourseById(Long courseId, CourseDTO courseDTO);


}
