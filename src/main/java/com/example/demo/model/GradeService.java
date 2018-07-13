package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class GradeService {
    @Autowired
    GradeRepository grades;

//Change method to find grades for student
//    public Iterable<Grade> findMyGrades(Daltonuser theUser, AcademicClass theClass)
//    {
//      return grades.findAllByStudentIsAndTheClass(theUser,theClass);
//    }

//    Change method to find grades for class
//    public Iterable <Grade> getClassGradesFor(AcademicClass theClass)
//    {
//        return grades.findAllByTheClass(theClass);
//    }

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

//    Change the method to read a transcript
    public Iterable <Grade> getMyTranscript(Daltonuser theStudent)
    {
        return grades.findAllByStudentIs(theStudent);
    }
}
