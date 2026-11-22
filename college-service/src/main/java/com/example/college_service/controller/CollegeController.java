package com.example.college_service.controller;

import com.example.college_service.entity.College;
import com.example.college_service.service.CollegeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/colleges")
public class CollegeController {

    private final CollegeService collegeService;

    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @PostMapping("/")
    public ResponseEntity<College> saveCollege(@RequestBody College college){
        College c = collegeService.saveCollege(college);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @GetMapping("/")
    public ResponseEntity<List<College>> getAllColleges(){
        return ResponseEntity.ok(collegeService.getAllColleges());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<?>> findById(@PathVariable Long id){
        Optional<?> c = collegeService.getCollegeById(id);
        if (c.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(collegeService.getCollegeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<College> updateCollege(@RequestBody College college, @PathVariable Long id){
        return ResponseEntity.ok(collegeService.updateCollege(id, college));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollege(@PathVariable Long id){

        boolean c =  collegeService.deleteCollege(id);

        if (c){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return  ResponseEntity.notFound().build();

    }

}
