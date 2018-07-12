package com.example.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Daltonuser student;

    @ManyToOne
    private AcademicClass theClass;

    private double theGrade;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTheGrade() {
        return theGrade;
    }

    public Daltonuser getStudent() {
        return student;
    }

    public void setStudent(Daltonuser student) {
        this.student = student;
    }

    public void setTheGrade(double theGrade) {
        this.theGrade = theGrade;
    }


    public AcademicClass getTheClass() {
        return theClass;
    }

    public void setTheClass(AcademicClass theClass) {
        this.theClass = theClass;
    }
}
