package com.example.StudentExamManagementSystem.service;

import com.example.StudentExamManagementSystem.exceptions.ResourceNotFoundException;
import com.example.StudentExamManagementSystem.model.Course;
import com.example.StudentExamManagementSystem.model.StudentCourse;
import com.example.StudentExamManagementSystem.model.User;
import com.example.StudentExamManagementSystem.payload.CourseDTO;
import com.example.StudentExamManagementSystem.payload.StudentCourseDTO;
import com.example.StudentExamManagementSystem.repositories.CourseRepository;
import com.example.StudentExamManagementSystem.repositories.StudentCourseRepository;
import com.example.StudentExamManagementSystem.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentCourseServiceImpl implements  StudentCourseService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public StudentCourseDTO createStudentCourse(StudentCourseDTO studentCourseDTO, User user) {
        try {
            // Map the DTO to the entity
            StudentCourse studentCourse = modelMapper.map(studentCourseDTO, StudentCourse.class);
System.out.println("studentCourse"+studentCourse);
            // Set the user for the studentCourse
            studentCourse.setUser(user);

            // Set the creation date
            studentCourse.setCreatedAt(new Date());

            // If the DTO contains a course ID, retrieve and set the course entity
            if (studentCourseDTO.getCourseId() != null) {
                Course course = courseRepository.findById(studentCourseDTO.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
                studentCourse.setCourse(course);
            }

            // Add the studentCourse to the user's list of studentCourses
            List<StudentCourse> studentCoursesList = user.getStudentCourses();
            studentCoursesList.add(studentCourse);
            user.setStudentCourses(studentCoursesList);

            // Save the studentCourse entity
            StudentCourse savedStudentCourse = studentCourseRepository.save(studentCourse);

            // Map the saved entity back to a DTO and return it
            return modelMapper.map(savedStudentCourse, StudentCourseDTO.class);
        } catch (Exception e) {
            // Handle exception and provide a meaningful message or log the error
            throw new RuntimeException("Failed to create StudentCourse", e);
        }
    }


    @Override
    public StudentCourseDTO getStudentCourseById(Long studentCourseId) {
        try {
            StudentCourse studentCourse = studentCourseRepository.findById(studentCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("StudentCourse", "studentCourseId", studentCourseId));
            return modelMapper.map(studentCourse, StudentCourseDTO.class);
        } catch (Exception e) {
            // Handle exception and provide a meaningful message or log the error
            throw new RuntimeException("Failed to get StudentCourse by ID", e);
        }
    }

    @Override
    public List<StudentCourseDTO> getAllStudentCourses() {
        try {
            List<StudentCourse> studentCourses = studentCourseRepository.findAll();
            return studentCourses.stream()
                .map(studentCourse -> modelMapper.map(studentCourse, StudentCourseDTO.class))
                .collect(Collectors.toList());
        } catch (Exception e) {
            // Handle exception and provide a meaningful message or log the error
            throw new RuntimeException("Failed to get all StudentCourses", e);
        }
    }


    @Override
    public StudentCourseDTO updateStudentCourse(Long studentCourseId, StudentCourseDTO studentCourseDTO) {
        try {
            StudentCourse existingStudentCourse = studentCourseRepository.findById(studentCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("StudentCourse", "studentCourseId", studentCourseId));

            // Fetch and set the Course entity
            Course course = courseRepository.findById(studentCourseDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "courseId", studentCourseDTO.getCourseId()));
            existingStudentCourse.setCourse(course);

            // Fetch and set the User entity if needed
            if (studentCourseDTO.getUserId() != null) {
                User user = userRepository.findById(studentCourseDTO.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "userId", studentCourseDTO.getUserId()));
                existingStudentCourse.setUser(user);
            }

            existingStudentCourse.setEnrollmentDate(studentCourseDTO.getEnrollmentDate());

            StudentCourse updatedStudentCourse = studentCourseRepository.save(existingStudentCourse);
            return modelMapper.map(updatedStudentCourse, StudentCourseDTO.class);
        } catch (Exception e) {
            // Handle exception and provide a meaningful message or log the error
            throw new RuntimeException("Failed to update StudentCourse", e);
        }
    }


    @Override
    public String deleteStudentCourse(Long studentCourseId, User user) {
        StudentCourse existingStudentCourse = studentCourseRepository.findById(studentCourseId)
            .orElseThrow(() -> new ResourceNotFoundException("StudentCourse", "studentCourseId", studentCourseId));

        if (!existingStudentCourse.getUser().getUserId().equals(user.getUserId()) && !isAdmin(user)) {
            throw new RuntimeException("You do not have permission to delete this student course");
        }

        studentCourseRepository.delete(existingStudentCourse);
        return "Student course deleted successfully";
    }

    private boolean isAdmin(User user) {
        return user.getRoles().stream().anyMatch(role -> role.getRoleName().equals("ROLE_ADMIN"));
    }
}

