package com.satxvitalrecords.controllers;
import com.satxvitalrecords.models.Record;
import com.satxvitalrecords.repositories.ApplicationRepo;
import com.satxvitalrecords.repositories.RecordRepo;
import com.satxvitalrecords.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ApplicationController {

    @Autowired
    private ApplicationRepo appDao;

    @Autowired
    private UserRepo userDao;

    @Autowired
    private RecordRepo recordDao;


    @GetMapping("/application-1")
    public String showApplication1(Model model) {
      model.addAttribute("record", new Record());
      return "application-1";
    }

    @PostMapping("/application-1")
    public String saveRecord(@ModelAttribute Record record){
    recordDao.save(record);
    return "redirect:/application-2";
    }

    @GetMapping("/application-2")
    public String showApplication2() {
        return "application-2";
    }

    @PostMapping("/application-2")
    public String saveApp2() {
      Record record1= recordDao.findOne(1L);
      recordDao.save(record1);
      return "redirect:/application-3";
    }

    @GetMapping("/application-3")
    public String showApplication3() {
        return "application-3";
    }

    @PostMapping("/application-3")
    public String saveApp3() {
        return "redirect:/application-4";
    }

    @GetMapping("/application-4")
    public String showApplication4() {
        return "application-4";
    }

    @PostMapping("/application-4")
    public String saveApp4() {
        return "redirect:/payment";
    }

    @GetMapping("/payment")
    public String showPayments() {
        return "payment";
    }

    @GetMapping("/completed-application")
    public String completedApplication(){
        return "completed-application";
    }

}
