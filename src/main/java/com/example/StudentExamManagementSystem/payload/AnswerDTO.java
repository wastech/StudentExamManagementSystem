package com.example.StudentExamManagementSystem.payload;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
    private Long answerId;
    private Long questionId;
    private String answerText;
    private boolean correct;
    private Date createdAt;
}