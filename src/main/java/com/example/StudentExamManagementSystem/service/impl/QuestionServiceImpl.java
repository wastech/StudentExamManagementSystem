package com.example.StudentExamManagementSystem.service.impl;

import com.example.StudentExamManagementSystem.model.Question;
import com.example.StudentExamManagementSystem.payload.QuestionDTO;
import com.example.StudentExamManagementSystem.repositories.QuestionRepository;
import com.example.StudentExamManagementSystem.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl  implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        boolean exists = questionRepository.existsByQuestionText(questionDTO.getQuestionText());
        if (exists) {
            throw new RuntimeException("Question already exists");
        }
        Question question = modelMapper.map(questionDTO, Question.class);
        Question savedQuestion = questionRepository.save(question);
        return modelMapper.map(savedQuestion, QuestionDTO.class);
    }

    @Override
    public QuestionDTO getQuestionById(Long questionId) {
        Question question = questionRepository.findById(questionId)
            .orElseThrow(() -> new RuntimeException("Question not found"));
        return modelMapper.map(question, QuestionDTO.class);
    }

    @Override
    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
            .map(question -> modelMapper.map(question, QuestionDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public QuestionDTO updateQuestion(Long questionId, QuestionDTO questionDTO) {
        Question question = questionRepository.findById(questionId)
            .orElseThrow(() -> new RuntimeException("Question not found"));


        boolean exists = questionRepository.existsByQuestionTextAndQuestionIdNot(questionDTO.getQuestionText(), questionId);
        if (exists) {
            throw new RuntimeException("Another question with the same text already exists");
        }

        question.setQuestionText(questionDTO.getQuestionText());
        question.setQuestionType(questionDTO.getQuestionType());

        Question updatedQuestion = questionRepository.save(question);
        return modelMapper.map(updatedQuestion, QuestionDTO.class);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId)
            .orElseThrow(() -> new RuntimeException("Question not found"));
        questionRepository.delete(question);
    }
}
