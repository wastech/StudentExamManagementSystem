package com.example.StudentExamManagementSystem.service.impl;

import com.example.StudentExamManagementSystem.exceptions.ResourceNotFoundException;
import com.example.StudentExamManagementSystem.model.Course;
import com.example.StudentExamManagementSystem.model.User;
import com.example.StudentExamManagementSystem.payload.CourseDTO;
import com.example.StudentExamManagementSystem.repositories.CourseRepository;
import com.example.StudentExamManagementSystem.repositories.UserRepository;
import com.example.StudentExamManagementSystem.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;





@Service
public class CourseServiceImpl  implements CourseService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;
    @Override
    public CourseDTO createCourse(CourseDTO courseDTO, User user) {
        Course course = modelMapper.map(courseDTO ,
            Course.class);
        course.setUser(user);
        List<Course> coursesList = user.getCourses();
        coursesList.add(course);
        user.setCourses(coursesList);
        Course savedCourse = courseRepository.save(course);
        return modelMapper.map(savedCourse, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> getcourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
            .map(course -> modelMapper.map(course, CourseDTO.class))
            .toList();

    }

    @Override
    public CourseDTO getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new ResourceNotFoundException("course", "courseId", courseId));
        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public List<CourseDTO> getUserCourses(User user) {
        List<Course> courses = user.getCourses();
        return courses.stream()
            .map(course -> modelMapper.map(course, CourseDTO.class))
            .toList();
    }

    @Override
    public String deleteCourseById(Long courseId) {
        Course courseFromDatabase = courseRepository.findById(courseId)
            .orElseThrow(() -> new ResourceNotFoundException("Course", "addressId", courseId));

        User user = courseFromDatabase.getUser();
        user.getCourses().removeIf(address -> address.getCourseId().equals(courseId));
        userRepository.save(user);

        courseRepository.delete(courseFromDatabase);

        return "Address deleted successfully with courseId: " + courseId;
    }


    @Override
    public CourseDTO updateCourseById(Long courseId, CourseDTO courseDTO) {
        Course courseFromDatabase = courseRepository.findById(courseId)
            .orElseThrow(() -> new ResourceNotFoundException("Course", "courseId", courseId));

        courseFromDatabase.setCourseName(courseDTO.getCourseName());
        courseFromDatabase.setCourseDescription(courseDTO.getCourseDescription());

        Course updatedAddress = courseRepository.save(courseFromDatabase);

        User user = courseFromDatabase.getUser();
        user.getCourses().removeIf(address -> address.getCourseId().equals(courseId));
        user.getCourses().add(updatedAddress);
        userRepository.save(user);

        return modelMapper.map(updatedAddress, CourseDTO.class);
    }
}
