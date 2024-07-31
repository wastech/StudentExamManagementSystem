package com.example.StudentExamManagementSystem.payload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAnswerDTO {
    private Long studentAnswerId;
    private Long userId;
    private Long examId;
    private Long questionId;
    private Long answerId;
    private String answerText;
}