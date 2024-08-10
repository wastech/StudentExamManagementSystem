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

        int totalQuestions = questionRepository.countByExam(exam);


        List<StudentAnswer> studentAnswers = studentAnswerRepository.findByExamAndUser(exam, user);

        int correctAnswers = (int) studentAnswers.stream()
            .filter(StudentAnswer::getIsCorrect)
            .count();

        System.out.println("correctAnswers"+ correctAnswers);

        // Calculate the percentage score
        float percentageScore = ((float) correctAnswers / totalQuestions) * 100;

        ExamResult examResult = new ExamResult();
        examResult.setUser(user);
        examResult.setExam(exam);
        examResult.setScore((float) correctAnswers);
        examResult.setPercentageScore(percentageScore);
        examResult.setResultDate(new Date());

        ExamResult savedExamResult = examResultRepository.save(examResult);

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

        int totalQuestions = questionRepository.countByExam(exam);
        int correctAnswers = (int) studentAnswerRepository.findByExamAndUser(exam, user).stream()
            .filter(StudentAnswer::getIsCorrect)
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
