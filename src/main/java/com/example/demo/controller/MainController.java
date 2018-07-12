package com.example.demo.controller;

import com.example.demo.model.ClassRepository;
import com.example.demo.model.DaltonUserRepository;
import com.example.demo.model.Daltonuser;
import com.sun.org.apache.bcel.internal.generic.DALOAD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    ClassRepository classes;

    @Autowired
    DaltonUserRepository users;

    @RequestMapping("/")
    public String showIndex(Model model)
    {
        return "dashboard";
    }

    @RequestMapping("/listclasses")
    public String listClasses(Model model ,Authentication auth)
    {
        //  Get current user as app user
        Daltonuser daltonuser = users.findByUsername(auth.getName());
        model.addAttribute("thisUser",daltonuser);
        model.addAttribute("classes",classes.findAll());
        return "listviews/classes";
    }
    @RequestMapping("/listmyclasses")
    public String listMyClasses(Model model, Authentication auth)
    {
        // Get current user as app user
        Daltonuser daltonuser = users.findByUsername(auth.getName());
        model.addAttribute("thisUser",daltonuser);
        System.out.println("User instructs:"+daltonuser.getInstructs());
        return "myclasses";
    }

    @RequestMapping("/availableclasses")
    public String listAvailableClasses(Model model, Authentication auth)
    {
        //  Get current user as app user
        Daltonuser daltonuser = users.findByUsername(auth.getName());
        model.addAttribute("thisUser",daltonuser);       model.addAttribute("classes",classes.findAllByDaltonusersNotContaining(users.findByUsername(auth.getName())));
        return "listviews/classes";
    }


    @GetMapping("/search")
    public String showSearchValues(Model model)
    {
        return "index";
    }
    @PostMapping("/search")
    public String searchClasses(Model model, HttpServletRequest request, Authentication auth)
    {
        //  Get current user as app user
        Daltonuser daltonuser = users.findByUsername(auth.getName());
        model.addAttribute("thisUser",daltonuser);      String searchString = request.getParameter("search");
        model.addAttribute("classes",classes.findAllByClassNameContainingIgnoreCaseOrSectionCodeContainingIgnoreCase(searchString,searchString));
        return "listviews/classes";
    }
}
