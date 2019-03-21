package com.satxvitalrecords.controllers;

import com.satxvitalrecords.models.Application;
import com.satxvitalrecords.models.Record;
import com.satxvitalrecords.models.User;
import com.satxvitalrecords.repositories.ApplicationRepo;
import com.satxvitalrecords.repositories.RecordRepo;
import com.satxvitalrecords.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    private ApplicationRepo appDao;

    @Autowired
    private UserRepo userDao;

  @Autowired
  private RecordRepo recordDao;

//  ----  RELOCATED TO USER CONTROLLER ----

//    @GetMapping("/")
//    public String home() {
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



    @PostMapping("/app-index")
    public String leaveComment(@ModelAttribute Application app) {
        appDao.save(app);
        return "redirect: /app-index";
    }


    @GetMapping("/app-view/{id}")
    public String viewAllApps(@PathVariable long id, Model model){
        Application app = appDao.findOne(id);
        Record recordDB = null;

        Iterable<Record> allrecords = recordDao.findAll();
        for(Record record : allrecords){
            if(record.getApplication() == app){
                recordDB = record;
            }
        }




        model.addAttribute("app", app);
        model.addAttribute("record", recordDB);
        return "app-view";
    }

    @PostMapping("/app-view/{id}")
    public String leaveComment(@PathVariable long id, @RequestParam (name= "comments") String comments) {
//        Date date = new Date();
//        DateFormat dateFormat = new SimpleDateFormat("YY/MM/DD hh:mm:ss");
//        dateFormat.format(date);
        Application app = appDao.findOne(id);
        app.setComments(comments);
//        Date date = new Date();
//        String strDateFormat = "YY/MM/DD hh:mm:ss";
//        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
//       dateFormat.format(date);
//        app.setComment_dateTime(date);
        appDao.save(app);
        return "redirect: /app-index";
    }

    @GetMapping("/bc-info")
    public String showBcInfoPage() {
        return "bc-info";
    }

    @PostMapping("/bc-info")
    public String startBcApp(@ModelAttribute Application app) {
        appDao.save(app);
        return "redirect: application-1";
    }

    @GetMapping("/dc-info")
    public String showDcInfoPage() {
        return "dc-info";
    }

    @PostMapping("/dc-info")
    public String startDcApp(@ModelAttribute Application app) {
        appDao.save(app);
        return "redirect: application-1";
    }



}
