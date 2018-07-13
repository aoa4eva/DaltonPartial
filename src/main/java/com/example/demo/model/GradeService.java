package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class GradeService {
    @Autowired
    GradeRepository grades;

    public void setMyGrade(Daltonuser theStudent, AcademicClass theClass, double theGrade)
    {

        Grade aGrade = grades.findByStudentIsAndTheClass(theStudent,theClass);

        if(aGrade==null)
        {
            aGrade = new Grade();
        }
        aGrade.setStudent(theStudent);
        aGrade.setTheClass(theClass);
        aGrade.setTheGrade(theGrade);
        grades.save(aGrade);
    }

}
