package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AcademicClass implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long crn;

    private long courseNo;

    private String className;

    private String subjectCode;

    private String sectionCode;

    private String semesterCode;

    @ManyToMany(mappedBy = "instructs")
    private Set<Daltonuser> instructors;

    @ManyToMany(mappedBy = "takes")
    private Set<Daltonuser> daltonusers;

    @OneToMany(mappedBy = "theClass")
    private Set <Grade> grades;

    public AcademicClass() {
        this.instructors = new HashSet<>();
        this.daltonusers = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCrn() {
        return crn;
    }

    public void setCrn(long crn) {
        this.crn = crn;
    }

    public long getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(long courseNo) {
        this.courseNo = courseNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }


    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }


    public Set<Daltonuser> getDaltonusers() {
        return daltonusers;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public Set<Daltonuser> getInstructors() {
        return instructors;
    }

    public void setInstructors(Set<Daltonuser> instructors) {
        this.instructors = instructors;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setDaltonusers(Set<Daltonuser> daltonusers) {
        this.daltonusers = daltonusers;
    }


    @OneToMany(mappedBy = "theClass")
    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "AcademicClass{" +
                "id=" + id +
                ", crn=" + crn +
                ", courseNo=" + courseNo +
                ", subjectCode='" + subjectCode + '\'' +
                ", sectionCode='" + sectionCode + '\'' +
                ", instructors=" + instructors +
                ", daltonusers=" + daltonusers +
                '}';
    }
}
