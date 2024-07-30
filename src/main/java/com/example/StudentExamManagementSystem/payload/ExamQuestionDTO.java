package com.example.StudentExamManagementSystem.payload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamQuestionDTO {
    private Long examQuestionId;
    private Long examId;
    private Long questionId;
}