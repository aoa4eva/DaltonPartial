package com.example.demo.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Daltonuser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private String major;

    @OneToOne
    private Instructor instructorDetails;


    @ManyToMany
    private Set<AcademicClass> takes;

    @ManyToMany
    private Set<AcademicClass> droppedClasses;


    @ManyToMany
    private Set<AcademicClass> instructs;

    @OneToMany(mappedBy = "student")
    private Set <Grade> studentGrade;

    @ManyToMany()
    private Set<AppRole> roles;

    public Daltonuser() {
        this.takes = new HashSet<>();
        this.instructs= new HashSet<>();
        this.droppedClasses  = new HashSet<>();
        this.roles = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

// Add security for this to be effective
   this.password = new BCryptPasswordEncoder().encode("password"); }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Instructor getInstructorDetails() {
        return instructorDetails;
    }

    public void setInstructorDetails(Instructor instructorDetails) {
        this.instructorDetails = instructorDetails;
    }

    public Set<AcademicClass> getTakes() {
        return takes;
    }

    public void setTakes(Set<AcademicClass> takes) {
        this.takes = takes;
    }

    public Set<AcademicClass> getInstructs() {
        return instructs;
    }

    public Set<AcademicClass> getDroppedClasses() {
        return droppedClasses;
    }

    public void setDroppedClasses(Set<AcademicClass> droppedClasses) {
        this.droppedClasses = droppedClasses;
    }

    public void setInstructs(Set<AcademicClass> instructs) {
        this.instructs = instructs;
    }

    public void enroll(AcademicClass academicClass)
    {
        this.takes.add(academicClass);
    }

    public void drop(AcademicClass academicClass)
    {
        this.takes.remove(academicClass);
        this.droppedClasses.add(academicClass);
    }

    public void teaches(AcademicClass academicClass)
    {
        this.instructs.add(academicClass);
    }

    public Set<Grade> getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(Set<Grade> studentGrade) {
        this.studentGrade = studentGrade;
    }

    public void addRole(AppRole role)
    {
        this.roles.add(role);
    }

    public Set<AppRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AppRole> roles) {
        this.roles = roles;
    }
}
