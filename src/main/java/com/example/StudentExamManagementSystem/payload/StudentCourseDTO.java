package com.example.StudentExamManagementSystem.payload;

import lombok.Data;
import java.util.Date;

@Data
public class StudentCourseDTO {
    private Long studentCourseId;
    private Long courseId;
    private Long userId;
    private Date enrollmentDate;
    private Date createdAt;
}
