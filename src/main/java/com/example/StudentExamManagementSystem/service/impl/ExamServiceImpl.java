package com.example.StudentExamManagementSystem.service.impl;

import com.example.StudentExamManagementSystem.exceptions.APIException;
import com.example.StudentExamManagementSystem.exceptions.ResourceNotFoundException;
import com.example.StudentExamManagementSystem.model.Course;
import com.example.StudentExamManagementSystem.model.User;
import com.example.StudentExamManagementSystem.payload.ExamDTO;
import java.util.List;
import java.util.stream.Collectors;

import com.example.StudentExamManagementSystem.model.Exam;
import com.example.StudentExamManagementSystem.repository.CourseRepository;
import com.example.StudentExamManagementSystem.repository.ExamRepository;
import com.example.StudentExamManagementSystem.repository.UserRepository;
import com.example.StudentExamManagementSystem.service.ExamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ExamDTO createExam(ExamDTO examDTO) {
        User user = userRepository.findById(examDTO.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User", "userId", examDTO.getUserId()));

        boolean exists = examRepository.findExistingExamForUser(user, examDTO.getExamDate()).isPresent();
        if (exists) {
            throw  new APIException("Student is already assigned to an exam at this time");
        }

        Exam exam = new Exam();
        exam.setExamName(examDTO.getExamName());
        exam.setExamDate(examDTO.getExamDate());
        exam.setDurationMinutes(examDTO.getDurationMinutes());

        if (examDTO.getCourseId() != null) {
            Course course = courseRepository.findById(examDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "courseId", examDTO.getCourseId()));
            exam.setCourse(course);
        }

        exam.setUser(user);
        Exam savedExam = examRepository.save(exam);

        // Add the exam to the user's list of exams
        user.getExams().add(savedExam);
        userRepository.save(user);

        ExamDTO savedExamDTO = modelMapper.map(savedExam, ExamDTO.class);
        savedExamDTO.setCourseId(savedExam.getCourse().getCourseId());
        savedExamDTO.setUserId(savedExam.getUser().getUserId());
        return savedExamDTO;
    }
    @Override
    public ExamDTO updateExam(Long examId, ExamDTO examDTO) {
        Exam exam = examRepository.findById(examId)
            .orElseThrow(() -> new ResourceNotFoundException("Exam", "examId", examId));

        User user = userRepository.findById(examDTO.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User", "userId", examDTO.getUserId()));

        boolean exists = examRepository.findExistingExamForUser(user, examDTO.getExamDate())
            .filter(e -> !e.getExamId().equals(examId)).isPresent();
        if (exists) {
            throw new APIException("Student is already assigned to an exam at this time");
        }

        exam.setExamName(examDTO.getExamName());
        exam.setExamDate(examDTO.getExamDate());
        exam.setDurationMinutes(examDTO.getDurationMinutes());

        if (examDTO.getCourseId() != null) {
            Course course = courseRepository.findById(examDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "courseId",examDTO.getCourseId()));
            exam.setCourse(course);
        }

        exam.setUser(user);
        Exam updatedExam = examRepository.save(exam);
        return modelMapper.map(updatedExam, ExamDTO.class);
    }
    @Override
    public ExamDTO deleteExam(Long examId) {
        Exam exam = examRepository.findById(examId)
            .orElseThrow(() -> new APIException("Exam not found"));
        examRepository.delete(exam);
        return modelMapper.map(exam, ExamDTO.class);
    }

    @Override
    public ExamDTO getExamById(Long examId) {
        Exam exam = examRepository.findById(examId)
            .orElseThrow(() -> new APIException("Exam not found"));
        return modelMapper.map(exam, ExamDTO.class);
    }

    @Override
    public Page<ExamDTO> getAllExams(Pageable pageable) {
        Page<Exam> examPage = examRepository.findAll(pageable);
        List<ExamDTO> examDTOs = examPage.getContent().stream()
            .map(exam -> modelMapper.map(exam, ExamDTO.class))
            .collect(Collectors.toList());
        return new PageImpl<>(examDTOs, pageable, examPage.getTotalElements());
    }
}