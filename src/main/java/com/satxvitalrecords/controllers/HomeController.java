package com.satxvitalrecords.controllers;

import com.satxvitalrecords.models.User;
import com.satxvitalrecords.repositories.ApplicationRepo;
import com.satxvitalrecords.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ApplicationRepo appDao;

    @Autowired
    private UserRepo userDao;

//    @GetMapping("/")
//    public String home() {
//        return "index";
//    }

    @GetMapping("/")
    public String showRegisterForm(Model model){
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/")
    public String registerHome(@ModelAttribute User user) {
        userDao.save(user);
        return "redirect: application-1";
    }


    @GetMapping("/app-index")
    public String viewAllApps(Model model){
        model.addAttribute("apps", appDao.findAll());
        return "app-index";
    }

//    @PostMapping("/app-index")
//    public String viewAllApps( @RequestParam int deleteId) {
//        postDao.delete(deleteId);
//        return "redirect:/posts";
//    }




}
