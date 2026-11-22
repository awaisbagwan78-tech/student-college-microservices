package com.example.college_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="colleges")
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long collegeId ;

    private String collegeName ;

    private String address ;

    private String university ;

    private String contactNumber ;

}
