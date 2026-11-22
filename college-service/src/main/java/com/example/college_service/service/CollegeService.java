package com.example.college_service.service;

import com.example.college_service.entity.College;
import com.example.college_service.repository.CollegeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollegeService {

    private final CollegeRepository collegeRepository;


    public CollegeService(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }


    public College saveCollege(College college){
        return collegeRepository.save(college);
    }

    public List<College> getAllColleges(){
        return collegeRepository.findAll();
    }

    public Optional<College> getCollegeById(Long id){
        return collegeRepository.findById(id);
    }

    public College updateCollege(Long id, College college){

        Optional<College> existing = collegeRepository.findById(id);
        //.orElseThrow(()-> new RuntimeException("Not Found"));

        if(existing.isEmpty()){
            throw new RuntimeException("Not found!");
        }


        College a = existing.get();
        a.setCollegeName(college.getCollegeName());
        a.setAddress(college.getAddress());
        a.setContactNumber(college.getContactNumber());
        a.setUniversity(college.getUniversity());



        return collegeRepository.save(a);
    }

    public boolean deleteCollege(Long id){
        Optional<College> c = collegeRepository.findById(id);
        if (c.isEmpty()){
            return false;
        }
        collegeRepository.deleteById(id);

        return true;
    }

}

