package com.example.student_service.dto;

import lombok.Data;

@Data
public class StudentResponseDto {

    private Long studentId;
    private String studentName;
    private String email;
    private String course;

    public CollegeDto college;
}
