package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String instructorCode;

    private String department;

    private String Office;

    @OneToOne(mappedBy = "instructorDetails")
    private Daltonuser theUser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInstructorCode() {
        return instructorCode;
    }

    public void setInstructorCode(String instructorCode) {
        this.instructorCode = instructorCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOffice() {
        return Office;
    }

    public void setOffice(String office) {
        Office = office;
    }

    public Daltonuser getTheUser() {
        return theUser;
    }

    public void setTheUser(Daltonuser theUser) {
        this.theUser = theUser;
    }
}
