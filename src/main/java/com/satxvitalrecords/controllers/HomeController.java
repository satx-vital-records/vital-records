package com.satxvitalrecords.controllers;

import com.satxvitalrecords.models.Application;
import com.satxvitalrecords.models.Record;
import com.satxvitalrecords.models.User;
import com.satxvitalrecords.repositories.ApplicationRepo;
import com.satxvitalrecords.repositories.RecordRepo;
import com.satxvitalrecords.repositories.StatusRepo;
import com.satxvitalrecords.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Controller
public class HomeController {

    @Autowired
    private ApplicationRepo appDao;

    @Autowired
    private UserRepo userDao;

    @Autowired
    private RecordRepo recordDao;

    @Autowired
    private StatusRepo statusDao;

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
    public String showSingleApp(Model model) {
        model.addAttribute("app", new Application());
        return "app-view";
    }

    @PostMapping("/app-view")
    public String updateSingleApp(@ModelAttribute Application app) {
        appDao.save(app);
        return "redirect: app-index";
    }

    @GetMapping("/app-index")
    public String viewAllApps(Model model) {
        int inprogress = numberOfApps(appDao.findAll(), "In Progress");
        int need_docs = numberOfApps(appDao.findAll(), "Need Uploads");
        int pending_review = numberOfApps(appDao.findAll(), "Pending Review");
        int approved = numberOfApps(appDao.findAll(), "Approved");
        int mailed = numberOfApps(appDao.findAll(), "Mailed");
        int pickedup = numberOfApps(appDao.findAll(), "Picked-up");
        System.out.println(inprogress);

        model.addAttribute("apps", appDao.findAll());
        model.addAttribute("inprogress", inprogress);
        model.addAttribute("pendingreview", pending_review);
        model.addAttribute("needdocs", need_docs);
        model.addAttribute("approved", approved);
        model.addAttribute("mailed", mailed);
        model.addAttribute("pickedup", pickedup);

        return "app-index";
    }


    @PostMapping("/app-index")
    public String leaveComment(@ModelAttribute Application app) {
        appDao.save(app);
        return "redirect: /app-index";
    }


    @GetMapping("/app-view/{id}")
    public String viewAllApps(@PathVariable long id, Model model) {
        Application app = appDao.findOne(id);
        Record record = recordDao.findOne(id);

        int inprogress = numberOfApps(appDao.findAll(), "In Progress");
        int need_docs = numberOfApps(appDao.findAll(), "Need Uploads");
        int pending_review = numberOfApps(appDao.findAll(), "Pending Review");
        int approved = numberOfApps(appDao.findAll(), "Approved");
        int mailed = numberOfApps(appDao.findAll(), "Mailed");
        int pickedup = numberOfApps(appDao.findAll(), "Picked-up");
        System.out.println(inprogress);

        model.addAttribute("apps", appDao.findAll());
        model.addAttribute("inprogress", inprogress);
        model.addAttribute("pendingreview", pending_review);
        model.addAttribute("needdocs", need_docs);
        model.addAttribute("approved", approved);
        model.addAttribute("mailed", mailed);
        model.addAttribute("pickedup", pickedup);


        model.addAttribute("app", app);
        model.addAttribute("record", record);
        return "app-view";
    }

    @PostMapping("/app-view/{id}")
    public String leaveComment(@PathVariable long id, @RequestParam(name = "comments") String comments) {
        Application app = appDao.findOne(id);
//        Date date = new Date();
//        DateFormat dateFormat = new SimpleDateFormat("YY/MM/DD hh:mm:ss");
//        dateFormat.format(date);
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
    public String showBcInfoPage(Model model) {
        model.addAttribute("app", new Application());
        return "bc-info";
    }

    @PostMapping("/bc-info")
    public String birthRecord(Application app, @RequestParam(name = "RT") String record_type, Model model) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(sessionUser.getId());
        model.addAttribute("app", app);

        app.setUser(userDB);
        app.setRecord_type(record_type);
        app.setStatus(statusDao.findOne(100L));
//        System.out.println(app.getRecord_type());
//        System.out.println(sessionUser.getFirst_name());
        appDao.save(app);
        return "redirect:/application-1";
    }

    @GetMapping("/dc-info")
    public String showDcInfoPage() {
        return "dc-info";
    }

    //    @PostMapping("/dc-info")
//    public String startDcApp(@ModelAttribute Application app) {
//        appDao.save(app);
//        return "redirect: application-1";
//    }
    @PostMapping("/dc-info")
    public String deathRecord(Application app, @RequestParam(name = "RT") String record_type, Model model) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(sessionUser.getId());
        app.setUser(userDB);
        app.setRecord_type(record_type);
        app.setStatus(statusDao.findOne(100L));
        appDao.save(app);
        return "redirect:/application-1";
    }


    public Integer numberOfApps(Iterable<Application> list, String status) {

        int count = 0;
        for (Application item : list) {
            if (item.getStatus().getDescription().equals(status)) {
                count++;
            }
        }
        return count;
    }


//        else {
//            int count = 0;
//            Iterator iterator = list.iterator();
//            while (iterator.hasNext()) {
//                iterator.next();
//                count++;
//            }
//            return count;
//            }



}
