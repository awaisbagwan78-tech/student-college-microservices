package com.example.student_service.controller;

import com.example.student_service.dto.StudentResponseDto;
import com.example.student_service.entity.Student;
import com.example.student_service.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){

        Student st = studentService.saveStudent(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(st);
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentDataById(
            @PathVariable Long id) {

        return studentService.getStudentDataById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Student>> updateStudent(@PathVariable Long id, @RequestBody Student student){
        Optional<Student> s = studentService.updateStudent(id,student);
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted){
            return "Student data is deleted.";
        }
        return "Student not found!";
    }

}
