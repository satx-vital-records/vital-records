package com.satxvitalrecords.controllers;

import com.satxvitalrecords.repositories.ApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ApplicationRepo appDao;

    @GetMapping("/")
    public String home() {
        return "index";
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
