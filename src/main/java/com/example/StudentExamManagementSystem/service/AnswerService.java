package com.example.StudentExamManagementSystem.service;

import com.example.StudentExamManagementSystem.payload.AnswerDTO;
import java.util.List;

public interface AnswerService {
    AnswerDTO createAnswer(AnswerDTO answerDTO);
    AnswerDTO getAnswerById(Long answerId);
    List<AnswerDTO> getAnswersByQuestionId(Long questionId);
    AnswerDTO updateAnswer(Long answerId, AnswerDTO answerDTO);
    void deleteAnswer(Long answerId);
}