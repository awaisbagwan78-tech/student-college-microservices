package com.example.student_service.client;

import com.example.student_service.dto.CollegeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COLLEGE-SERVICE")
public interface CollegeClient {

    @GetMapping("/colleges/{id}")
    CollegeDto getCollegeById(@PathVariable("id") Long id);

}

