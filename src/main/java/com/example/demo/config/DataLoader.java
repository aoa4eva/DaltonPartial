package com.example.demo.config;

import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    DaltonUserRepository students;

    @Autowired
    InstructorRepository instructors;

    @Autowired
    ClassRepository classes;

    @Autowired
    GradeRepository grades;

    @Autowired
    GradeService gradeService;

    @Autowired
    AppRoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading roles into the application");
        //Create new roles for the database
        AppRole aRole = new AppRole();
        aRole.setRole("TEACHER");
        roleRepository.save(aRole);

        aRole = new AppRole();
        aRole.setRole("STUDENT");
        roleRepository.save(aRole);

        aRole = new AppRole();
        aRole.setRole("ADMIN");
        roleRepository.save(aRole);


        AcademicClass aClass = new AcademicClass();
        aClass.setCourseNo(1010);
        aClass.setClassName("Biology 101");
        aClass.setSubjectCode("BIO");
        aClass.setCrn(10072);
        aClass.setSectionCode("Biology 1, Section 1, Monday 5:50pm");
        classes.save(aClass);

        AcademicClass anotherClass = new AcademicClass();
        anotherClass.setClassName("Biology 102");
        anotherClass.setCourseNo(1010);
        anotherClass.setSubjectCode("BIO");
        anotherClass.setCrn(10073);
        anotherClass.setSectionCode("Biology 2, Section 2, Monday 5:50pm");
        classes.save(anotherClass);

        Daltonuser aDaltonuser = new Daltonuser();
        aDaltonuser.setFirstName("Josh");
        aDaltonuser.setLastName("Tucker");
        aDaltonuser.setMajor("English");
        aDaltonuser.setPassword("password");
        aDaltonuser.setUsername("Josh");
        aDaltonuser.addRole(roleRepository.findByRole("STUDENT"));
        aDaltonuser.addRole(roleRepository.findByRole("TEACHER"));
        aDaltonuser.enroll(aClass);
        students.save(aDaltonuser);


        Daltonuser anotherStudent = new Daltonuser();
        anotherStudent.setFirstName("Rekik");
        anotherStudent.setLastName("Haile");
        anotherStudent.setMajor("Liberal Arts");
        anotherStudent.enroll(aClass);
        anotherStudent.setUsername("rekik");
        anotherStudent.setPassword("password");
        anotherStudent.addRole(roleRepository.findByRole("STUDENT"));
        anotherStudent.enroll(anotherClass);
        students.save(anotherStudent);

        aDaltonuser.teaches(anotherClass);
        students.save(aDaltonuser);

        System.out.println(aDaltonuser.getFirstName()+"'s classes:");
        for (AcademicClass eachAcademicClass : aDaltonuser.getTakes()) {
            System.out.println(eachAcademicClass.getSectionCode());
        }

        System.out.println(aDaltonuser.getFirstName()+"'s classes:");
        for (AcademicClass eachAcademicClass :aDaltonuser.getInstructs()) {
            System.out.println(eachAcademicClass.getSectionCode());

        }

        //Show class details
        System.out.println("All classes:");
        for (AcademicClass eachClass:classes.findAll())
        {
            System.out.println("Class:"+eachClass.getSubjectCode()+" - "+eachClass.getSectionCode());

            System.out.println("Teachers:");
            for (Daltonuser eachInstructor:eachClass.getInstructors()) {
                System.out.println(eachInstructor.getFirstName());
            }

            System.out.println("Students:");
            for (Daltonuser eachDaltonuser :eachClass.getDaltonusers()) {
                System.out.println(eachDaltonuser.getFirstName());
            }
        }

        gradeService.setMyGrade(aDaltonuser,aClass,20.5);
        gradeService.setMyGrade(anotherStudent,aClass,20.5);
        gradeService.setMyGrade(anotherStudent,anotherClass,25.5);

        //Transcript (or buy official transcript)
//        for(AcademicClass eachClass: anotherStudent.getTakes())
//        {
//            System.out.println("Class:"+eachClass.getSectionCode());
//           for(Grade eachGrade: gradeService.findMyGrades(anotherStudent,eachClass))
//            {
//                System.out.println("Grade:"+eachGrade.getTheGrade());
//            }
//        }






    }
}
