package com.example.StudentExamManagementSystem.payload;


import com.example.StudentExamManagementSystem.model.QuestionType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Long questionId;
    private String questionText;
    private QuestionType questionType;
}