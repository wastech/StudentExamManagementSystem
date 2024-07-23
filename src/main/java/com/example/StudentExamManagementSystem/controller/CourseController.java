package com.example.StudentExamManagementSystem.controller;


import com.example.StudentExamManagementSystem.model.User;
import com.example.StudentExamManagementSystem.payload.CourseDTO;
import com.example.StudentExamManagementSystem.service.CourseService;
import com.example.StudentExamManagementSystem.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    AuthUtil authUtil;

    @Autowired
    CourseService courseService;

    @PostMapping("/courses")
    public ResponseEntity<CourseDTO> createAddress(@Valid @RequestBody CourseDTO courseDTO){
        User user = authUtil.loggedInUser();
        CourseDTO savedCourseDTO = courseService.createCourse(courseDTO, user);
        return new ResponseEntity<>(savedCourseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getCourses(){
        List<CourseDTO> courseList = courseService.getcourses();
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<CourseDTO> getAddressById(@PathVariable Long courseId){
        CourseDTO courseDTO = courseService.getCourseById(courseId);
        return new ResponseEntity<>(courseDTO, HttpStatus.OK);
    }

    @GetMapping("/users/courses")
    public ResponseEntity<List<CourseDTO>> getUserAddresses(){
        User user = authUtil.loggedInUser();
        List<CourseDTO> courseList = courseService.getUserCourses(user);
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @PutMapping("/courses/{courseId}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long courseId
        , @RequestBody CourseDTO courseDTO){
        CourseDTO updatedCourse = courseService.updateCourseById(courseId, courseDTO);
        return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId){
        String status = courseService.deleteCourseById(courseId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

}
