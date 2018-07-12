package com.example.demo.controller;

import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class TestController {
    @Autowired
    ClassRepository classes;

    @Autowired
    AppRoleRepository roleRepository;

    @Autowired
    DaltonUserRepository userRepository;

    @Autowired
    GradeRepository grades;

    @RequestMapping("/index")
    public String showIndex(Model model, Authentication auth) {
        Daltonuser currentUser = userRepository.findByUsername(auth.getName());
        model.addAttribute("currentUser",currentUser);
        System.out.println(currentUser.getTakes());
        System.out.println(currentUser.getInstructs());
        return "index";
    }

    @GetMapping("/addclass")
    public String showAddClass(Model model) {
        model.addAttribute("aClass", new AcademicClass());
        return "addclass";
    }

    @PostMapping("/addclass")
    public String saveClass(@ModelAttribute("aClass") AcademicClass theClass, BindingResult result) {
        classes.save(theClass);
        return "redirect:/";
    }

    @RequestMapping("/editprofile")
    public String showEditProfile(Model model) {
        model.addAttribute("aUser", new Daltonuser());
        return "editprofile";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("newuser", new Daltonuser());
        model.addAttribute("roleList", roleRepository.findAll());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid @ModelAttribute("newuser") Daltonuser user, BindingResult result) {
        String thePassword = user.getPassword();
        if (result.hasErrors()) {
            return "register";
        }
        user.addRole(roleRepository.findByRole("STUDENT"));
        userRepository.save(user);
        return "redirect:/login";
    }

    @RequestMapping("/enroll")
    public String enrollUser(HttpServletRequest request, Authentication auth, RedirectAttributes model)
    {
        long classid = new Long(request.getParameter("classid"));
        Daltonuser currentUser = userRepository.findByUsername(auth.getName());
        AcademicClass theClass = classes.findById(classid).get();
        currentUser.enroll(theClass);
        userRepository.save(currentUser);
        for (Daltonuser student:theClass.getDaltonusers()) {
            //Check to see if grades have been assigned for a particular student and class
            ArrayList sizeThis = (ArrayList) grades.findAllByStudentIsAndTheClass(student,theClass);
            //If not, create a record in the grades table
            if(sizeThis.size()<1)
            {
                Grade aGrade = new Grade();
                aGrade.setStudent(student);
                aGrade.setTheClass(theClass);
                grades.save(aGrade);
                System.out.println("Created a grade for"+student.getFirstName()+" "+theClass.getSectionCode());
            }
        }
        model.addFlashAttribute("message","You have been enrolled in "+classes.findById(classid).get().getClassName());
        return "redirect:/";

    }

    @RequestMapping("/drop")
    public String dropCourse(HttpServletRequest request, Authentication auth, RedirectAttributes model)
    {
        long classid = new Long(request.getParameter("classid"));
        Daltonuser currentUser = userRepository.findByUsername(auth.getName());
        AcademicClass theClass = classes.findById(classid).get();
        currentUser.drop(theClass);
        userRepository.save(currentUser);
        for (Daltonuser student:theClass.getDaltonusers()) {
            //Check to see if grades have been assigned for a particular student and class
            ArrayList <Grade> sizeThis = (ArrayList) grades.findAllByStudentIsAndTheClass(currentUser,theClass);
            //If so, delete the grade
            for (Grade eachGrade:sizeThis) {
                grades.delete(eachGrade);
            }
        }
        model.addFlashAttribute("message","You have dropped "+classes.findById(classid).get().getClassName());
        return "redirect:/";

    }


    @RequestMapping("/roster")
    public String gradeClass(Model model, HttpServletRequest request)
    {
        long classid = new Long(request.getParameter("classid"));
        AcademicClass theClass = classes.findById(classid).get();
        model.addAttribute("aClass",theClass);
        System.out.println(theClass.toString());
        return "roster";
    }

//    Include this functionality for updating multiple objects at once
//    @RequestMapping("/editgrades")
//    public String editGrades(HttpServletRequest request, Authentication auth, Model model)
//    {
//        long classid = new Long(request.getParameter("classid"));
//        AcademicClass theClass = classes.findById(classid).get();
//        System.out.println("Grades for this class:"+theClass.getGrades().size());
//        model.addAttribute("aClass",theClass);
//        return "studentgrades";
//    }

    @GetMapping("/editgrades")
    public String showEdit(Model model, HttpServletRequest request)
    {   long classid = new Long(request.getParameter("theGrade"));
        Grade theGrade = grades.findById(classid).get();
        model.addAttribute("theGrade",theGrade);
        return "editgrade";
    }

    @PostMapping("/editgrades")
    public String saveEdit(@ModelAttribute("theGrade") Grade theGrade, RedirectAttributes model)
    {
        grades.save(theGrade);
        model.addFlashAttribute("message","Grade for"+theGrade.getStudent().getFirstName()+" "+theGrade.getStudent().getLastName()+" has been edited");
        return "redirect:/";

    }

    @RequestMapping("/savegrade")
    public String saveGrades(@ModelAttribute("theGrade") Grade theGrade, Model model)
    {
        grades.save(theGrade);
       return "redirect:/listmyclasses";
    }

}
