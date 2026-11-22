package com.example.student_service.service;

import com.example.student_service.client.CollegeClient;
import com.example.student_service.dto.CollegeDto;
import com.example.student_service.dto.StudentResponseDto;
import com.example.student_service.entity.Student;
import com.example.student_service.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CollegeClient collegeClient;

    public StudentService(StudentRepository studentRepository, CollegeClient collegeClient) {
        this.studentRepository = studentRepository;
        this.collegeClient = collegeClient;
    }

    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id){
return studentRepository.findById(id);
    }

    public Optional<StudentResponseDto> getStudentDataById(Long stdId) {

        return studentRepository.findById(stdId).map(student -> {

            CollegeDto college =
                    collegeClient.getCollegeById(student.getCollegeId());

            StudentResponseDto response = new StudentResponseDto();

            response.setStudentId(student.getStudentId());
            response.setStudentName(student.getStudentName());
            response.setEmail(student.getEmail());
            response.setCourse(student.getCourse());
            response.setCollege(college);

            return response;
        });
    }

    public Optional<Student> updateStudent(Long id, Student student){
        return studentRepository.findById(id)
                .map(existing->{
                    existing.setStudentName(student.getStudentName());
                    existing.setEmail(student.getEmail());
                    existing.setCourse(student.getCourse());
                    existing.setCollegeId(student.getCollegeId());

                    return studentRepository.save(existing);
                });
    }

    public boolean deleteStudent(Long id){
        if(!studentRepository.existsById(id)){
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

}
