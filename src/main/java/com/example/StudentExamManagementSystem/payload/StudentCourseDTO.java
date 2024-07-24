package com.example.StudentExamManagementSystem.payload;

import jakarta.persistence.Column;
import lombok.NonNull;

import java.util.Date;

public class StudentCourseDTO {
    private Long StudentCourseId;
    private Long courseId;
    private Date enrollmentDate;

}
