package com.example.StudentExamManagementSystem.service.impl;

import com.example.StudentExamManagementSystem.payload.ExamResultDTO;
import com.example.StudentExamManagementSystem.model.*;
import com.example.StudentExamManagementSystem.repository.*;
import com.example.StudentExamManagementSystem.service.ExamResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamResultServiceImpl implements ExamResultService {

    @Autowired
    private ExamResultRepository examResultRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ExamResultDTO createExamResult(ExamResultDTO examResultDTO) {
        User user = userRepository.findById(examResultDTO.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        Exam exam = examRepository.findById(examResultDTO.getExamId())
            .orElseThrow(() -> new RuntimeException("Exam not found"));

        // Calculate the total number of questions in the exam
        int totalQuestions = questionRepository.countByExam(exam);

        // Calculate the number of correct answers given by the student
        List<StudentAnswer> studentAnswers = studentAnswerRepository.findByExamAndUser(exam, user);
        int correctAnswers = (int) studentAnswers.stream()
            .filter(StudentAnswer::getCorrect)
            .count();

        // Calculate the percentage score
        float percentageScore = ((float) correctAnswers / totalQuestions) * 100;

        // Manually map the DTO to the entity
        ExamResult examResult = new ExamResult();
        examResult.setUser(user);
        examResult.setExam(exam);
        examResult.setScore((float) correctAnswers);
        examResult.setPercentageScore(percentageScore);
        examResult.setResultDate(new Date());

        // Save the exam result
        ExamResult savedExamResult = examResultRepository.save(examResult);

        // Manually map the entity to the DTO
        ExamResultDTO savedExamResultDTO = new ExamResultDTO();
        savedExamResultDTO.setUserId(savedExamResult.getUser().getUserId());
        savedExamResultDTO.setExamId(savedExamResult.getExam().getExamId());
        savedExamResultDTO.setScore(savedExamResult.getScore());
        savedExamResultDTO.setPercentageScore(savedExamResult.getPercentageScore());
        savedExamResultDTO.setResultDate(savedExamResult.getResultDate());

        return savedExamResultDTO;
    }

    @Override
    public ExamResultDTO getExamResultById(Long examResultId) {
        ExamResult examResult = examResultRepository.findById(examResultId)
            .orElseThrow(() -> new RuntimeException("ExamResult not found"));
        return modelMapper.map(examResult, ExamResultDTO.class);
    }

    @Override
    public List<ExamResultDTO> getAllExamResults() {
        return examResultRepository.findAll().stream()
            .map(examResult -> modelMapper.map(examResult, ExamResultDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public ExamResultDTO updateExamResult(Long examResultId, ExamResultDTO examResultDTO) {
        ExamResult examResult = examResultRepository.findById(examResultId)
            .orElseThrow(() -> new RuntimeException("ExamResult not found"));

        User user = userRepository.findById(examResultDTO.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        Exam exam = examRepository.findById(examResultDTO.getExamId())
            .orElseThrow(() -> new RuntimeException("Exam not found"));

        // Recalculate the total number of questions and correct answers
        int totalQuestions = questionRepository.countByExam(exam);
        System.out.println("totalQuestions"+ totalQuestions);
        int correctAnswers = (int) studentAnswerRepository.findByExamAndUser(exam, user).stream()
            .filter(StudentAnswer::getCorrect)
            .count();
        float percentageScore = ((float) correctAnswers / totalQuestions) * 100;

        examResult.setUser(user);
        examResult.setExam(exam);
        examResult.setScore((float) correctAnswers);
        examResult.setPercentageScore(percentageScore);
        examResult.setResultDate(examResultDTO.getResultDate());

        ExamResult updatedExamResult = examResultRepository.save(examResult);
        return modelMapper.map(updatedExamResult, ExamResultDTO.class);
    }


    @Override
    public void deleteExamResult(Long examResultId) {
        examResultRepository.deleteById(examResultId);
    }
}
