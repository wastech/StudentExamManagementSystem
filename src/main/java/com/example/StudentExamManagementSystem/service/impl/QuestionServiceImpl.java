package com.example.StudentExamManagementSystem.service.impl;

import com.example.StudentExamManagementSystem.exceptions.APIException;
import com.example.StudentExamManagementSystem.model.Exam;
import com.example.StudentExamManagementSystem.model.Question;
import com.example.StudentExamManagementSystem.payload.QuestionDTO;
import com.example.StudentExamManagementSystem.repository.ExamRepository;
import com.example.StudentExamManagementSystem.repository.QuestionRepository;
import com.example.StudentExamManagementSystem.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl  implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private ModelMapper modelMapper;



    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        boolean exists = questionRepository.existsByQuestionText(questionDTO.getQuestionText());
        if (exists) {
            throw new APIException("Question already exists");
        }

        // Fetch the Exam entity
        Exam exam = examRepository.findById(questionDTO.getExamId())
            .orElseThrow(() -> new APIException("Exam not found"));

        Question question = modelMapper.map(questionDTO, Question.class);
        question.setExam(exam); // Set the exam

        Question savedQuestion = questionRepository.save(question);
        return modelMapper.map(savedQuestion, QuestionDTO.class);
    }


    @Override
    public QuestionDTO getQuestionById(Long questionId) {
        Question question = questionRepository.findById(questionId)
            .orElseThrow(() -> new APIException("Question not found"));
        return modelMapper.map(question, QuestionDTO.class);
    }

    @Override
    public Page<QuestionDTO> getAllQuestions(Pageable pageable) {
        Page<Question> questionPage = questionRepository.findAll(pageable);
        List<QuestionDTO> questionDTOs = questionPage.getContent().stream()
            .map(question -> modelMapper.map(question, QuestionDTO.class))
            .collect(Collectors.toList());
        return new PageImpl<>(questionDTOs, pageable, questionPage.getTotalElements());
    }

    @Override
    public QuestionDTO updateQuestion(Long questionId, QuestionDTO questionDTO) {
        Question question = questionRepository.findById(questionId)
            .orElseThrow(() -> new APIException("Question not found"));

        boolean exists = questionRepository.existsByQuestionTextAndQuestionIdNot(questionDTO.getQuestionText(), questionId);
        if (exists) {
            throw new APIException("Another question with the same text already exists");
        }

        // Fetch the Exam entity
        Exam exam = examRepository.findById(questionDTO.getExamId())
            .orElseThrow(() -> new APIException("Exam not found"));

        question.setQuestionText(questionDTO.getQuestionText());
        question.setQuestionType(questionDTO.getQuestionType());
        question.setExam(exam); // Set the exam

        Question updatedQuestion = questionRepository.save(question);
        return modelMapper.map(updatedQuestion, QuestionDTO.class);
    }
    @Override
    public void deleteQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId)
            .orElseThrow(() -> new APIException("Question not found"));
        questionRepository.delete(question);
    }
}
