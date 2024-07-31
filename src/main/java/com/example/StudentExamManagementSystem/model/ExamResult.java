package com.example.StudentExamManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exam_results")
public class ExamResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_result_id")
    private Long examResultId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @Column(name = "score", nullable = false)
    private Float score;

    @Column(name = "percentage_score", nullable = false)
    private Float percentageScore;

    @Temporal(TemporalType.DATE)
    @Column(name = "result_date", nullable = false)
    private Date resultDate;

    @PrePersist
    protected void onCreate() {
        resultDate = new Date();
    }
}
