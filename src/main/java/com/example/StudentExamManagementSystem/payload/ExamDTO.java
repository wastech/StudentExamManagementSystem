package com.example.StudentExamManagementSystem.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamDTO {
    private String examName;
    private Long courseId;
    private Long userId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date examDate;

    private int durationMinutes;

}