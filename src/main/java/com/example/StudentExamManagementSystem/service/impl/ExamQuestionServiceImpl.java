package com.example.StudentExamManagementSystem.service.impl;


import com.example.StudentExamManagementSystem.payload.ExamQuestionDTO;
import com.example.StudentExamManagementSystem.model.Exam;
import com.example.StudentExamManagementSystem.model.ExamQuestion;
import com.example.StudentExamManagementSystem.model.Question;

import com.example.StudentExamManagementSystem.repositories.ExamQuestionRepository;
import com.example.StudentExamManagementSystem.repositories.ExamRepository;
import com.example.StudentExamManagementSystem.repositories.QuestionRepository;
import com.example.StudentExamManagementSystem.service.ExamQuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamQuestionServiceImpl implements ExamQuestionService {

    @Autowired
    private ExamQuestionRepository examQuestionRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ExamQuestionDTO addQuestionToExam(ExamQuestionDTO examQuestionDTO) {
        Exam exam = examRepository.findById(examQuestionDTO.getExamId())
            .orElseThrow(() -> new RuntimeException("Exam not found"));
        Question question = questionRepository.findById(examQuestionDTO.getQuestionId())
            .orElseThrow(() -> new RuntimeException("Question not found"));

        ExamQuestion examQuestion = new ExamQuestion();
        examQuestion.setExam(exam);
        examQuestion.setQuestion(question);
        examQuestion.setCreatedAt(new Date());

        ExamQuestion savedExamQuestion = examQuestionRepository.save(examQuestion);
        return modelMapper.map(savedExamQuestion, ExamQuestionDTO.class);
    }

    @Override
    public List<ExamQuestionDTO> getQuestionsByExamId(Long examId) {
        List<ExamQuestion> examQuestions = examQuestionRepository.findAll()
            .stream()
            .filter(eq -> eq.getExam().getExamId().equals(examId))
            .collect(Collectors.toList());
        return examQuestions.stream()
            .map(eq -> modelMapper.map(eq, ExamQuestionDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public void removeQuestionFromExam(Long examQuestionId) {
        examQuestionRepository.deleteById(examQuestionId);
    }
}