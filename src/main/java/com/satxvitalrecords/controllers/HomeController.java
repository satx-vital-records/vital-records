package com.satxvitalrecords.controllers;

import com.satxvitalrecords.models.Application;
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


//  ----  RELOCATED TO USER CONTROLLER ----

//    @GetMapping("/")
//    public String showHomePageForm(Model model){
//        model.addAttribute("user", new User());
//        return "index";
//    }

//    @PostMapping("/")
//    public String saveUserHome(@ModelAttribute User user) {
//        userDao.save(user);
//        return "redirect: login";
//    }


    @GetMapping("/app-view")
    public String showSingleApp(Model model){
        model.addAttribute("app", new Application());
        return "app-view";
    }

    @PostMapping("/app-view")
    public String updateSingleApp(@ModelAttribute Application app) {
        appDao.save(app);
        return "redirect: app-index";
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
