package com.example.StudentExamManagementSystem.service.impl;

import com.example.StudentExamManagementSystem.payload.StudentAnswerDTO;
import com.example.StudentExamManagementSystem.model.*;
import com.example.StudentExamManagementSystem.repository.*;
import com.example.StudentExamManagementSystem.service.StudentAnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentAnswerServiceImpl implements StudentAnswerService {

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentAnswerDTO createStudentAnswer(StudentAnswerDTO studentAnswerDTO) {
        // Fetch related entities
        User user = userRepository.findById(studentAnswerDTO.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        Exam exam = examRepository.findById(studentAnswerDTO.getExamId())
            .orElseThrow(() -> new RuntimeException("Exam not found"));
        Question question = questionRepository.findById(studentAnswerDTO.getQuestionId())
            .orElseThrow(() -> new RuntimeException("Question not found"));
        Answer answer = answerRepository.findById(studentAnswerDTO.getAnswerId())
            .orElseThrow(() -> new RuntimeException("Answer not found"));

        // Check if the provided answerText matches the correct answer
        boolean isCorrectAnswer = answer.getAnswerText().equals(studentAnswerDTO.getAnswerText())
            && answer.isCorrect();

        // Map DTO to entity
        StudentAnswer studentAnswer = modelMapper.map(studentAnswerDTO, StudentAnswer.class);
        studentAnswer.setUser(user);
        studentAnswer.setExam(exam);
        studentAnswer.setQuestion(question);
        studentAnswer.setAnswer(answer);
        studentAnswer.setIsCorrect(isCorrectAnswer);

        // Save and return the result
        StudentAnswer savedStudentAnswer = studentAnswerRepository.save(studentAnswer);
        return modelMapper.map(savedStudentAnswer, StudentAnswerDTO.class);
    }

    @Override
    public StudentAnswerDTO getStudentAnswerById(Long studentAnswerId) {
        StudentAnswer studentAnswer = studentAnswerRepository.findById(studentAnswerId)
            .orElseThrow(() -> new RuntimeException("StudentAnswer not found"));
        return modelMapper.map(studentAnswer, StudentAnswerDTO.class);
    }

    @Override
    public List<StudentAnswerDTO> getAllStudentAnswers() {
        return studentAnswerRepository.findAll().stream()
            .map(studentAnswer -> modelMapper.map(studentAnswer, StudentAnswerDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public StudentAnswerDTO updateStudentAnswer(Long studentAnswerId, StudentAnswerDTO studentAnswerDTO) {
        StudentAnswer studentAnswer = studentAnswerRepository.findById(studentAnswerId)
            .orElseThrow(() -> new RuntimeException("StudentAnswer not found"));

        User user = userRepository.findById(studentAnswerDTO.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        Exam exam = examRepository.findById(studentAnswerDTO.getExamId())
            .orElseThrow(() -> new RuntimeException("Exam not found"));
        Question question = questionRepository.findById(studentAnswerDTO.getQuestionId())
            .orElseThrow(() -> new RuntimeException("Question not found"));
        Answer answer = answerRepository.findById(studentAnswerDTO.getAnswerId())
            .orElseThrow(() -> new RuntimeException("Answer not found"));

        studentAnswer.setUser(user);
        studentAnswer.setExam(exam);
        studentAnswer.setQuestion(question);
        studentAnswer.setAnswer(answer);
        studentAnswer.setAnswerText(studentAnswerDTO.getAnswerText());

        StudentAnswer updatedStudentAnswer = studentAnswerRepository.save(studentAnswer);
        return modelMapper.map(updatedStudentAnswer, StudentAnswerDTO.class);
    }

    @Override
    public void deleteStudentAnswer(Long studentAnswerId) {
        studentAnswerRepository.deleteById(studentAnswerId);
    }
}
