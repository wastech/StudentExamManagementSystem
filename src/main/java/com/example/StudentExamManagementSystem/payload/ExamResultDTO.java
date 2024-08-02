package com.example.StudentExamManagementSystem.payload;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResultDTO {
    private Long userId;
    private Long examId;
    private Float score;
    private Float percentageScore;
    private Date resultDate;
}