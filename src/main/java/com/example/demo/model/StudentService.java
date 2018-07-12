package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    DaltonUserRepository students;

    @Autowired
    GradeRepository grades;

}
