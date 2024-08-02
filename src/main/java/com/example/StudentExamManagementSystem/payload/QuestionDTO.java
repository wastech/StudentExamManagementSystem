package com.example.StudentExamManagementSystem.payload;


import com.example.StudentExamManagementSystem.model.QuestionType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private String questionText;
    private QuestionType questionType;
    private Long examId;
}