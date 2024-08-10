package com.example.StudentExamManagementSystem.service.impl;

import com.example.StudentExamManagementSystem.payload.AnswerDTO;
import com.example.StudentExamManagementSystem.model.Answer;
import com.example.StudentExamManagementSystem.model.Question;
import com.example.StudentExamManagementSystem.repository.AnswerRepository;
import com.example.StudentExamManagementSystem.repository.QuestionRepository;
import com.example.StudentExamManagementSystem.service.AnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AnswerDTO createAnswer(AnswerDTO answerDTO) {
        Question question = questionRepository.findById(answerDTO.getQuestionId())
            .orElseThrow(() -> new RuntimeException("Question not found"));

        Answer answer = modelMapper.map(answerDTO, Answer.class);
        answer.setQuestion(question);
        answer.setCreatedAt(new Date());
        Answer savedAnswer = answerRepository.save(answer);
        return modelMapper.map(savedAnswer, AnswerDTO.class);
    }

    @Override
    public AnswerDTO getAnswerById(Long answerId) {
        Answer answer = answerRepository.findById(answerId)
            .orElseThrow(() -> new RuntimeException("Answer not found"));
        return modelMapper.map(answer, AnswerDTO.class);
    }

    @Override
    public List<AnswerDTO> getAnswersByQuestionId(Long questionId) {
        List<Answer> answers = answerRepository.findAll()
            .stream()
            .filter(answer -> answer.getQuestion().getQuestionId().equals(questionId))
            .collect(Collectors.toList());
        return answers.stream()
            .map(answer -> modelMapper.map(answer, AnswerDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public AnswerDTO updateAnswer(Long answerId, AnswerDTO answerDTO) {
        Answer answer = answerRepository.findById(answerId)
            .orElseThrow(() -> new RuntimeException("Answer not found"));

        answer.setAnswerText(answerDTO.getAnswerText());
        answer.setCorrect(answerDTO.getIsCorrect());
        Answer updatedAnswer = answerRepository.save(answer);

        return modelMapper.map(updatedAnswer, AnswerDTO.class);
    }

    @Override
    public void deleteAnswer(Long answerId) {
        answerRepository.deleteById(answerId);
    }
}
