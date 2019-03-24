package com.satxvitalrecords.controllers;
import com.satxvitalrecords.models.*;
import com.satxvitalrecords.repositories.*;
import com.satxvitalrecords.services.PdfStamper;
import com.sun.javaws.security.AppPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
//@SessionAttributes("user")
public class ApplicationController {

    @Autowired
    private ApplicationRepo appDao;

    @Autowired
    private UserRepo userDao;

    @Autowired
    private RecordRepo recordDao;

    @Autowired
    private AddressRepo mailDao;

    @Autowired
    private StatusRepo statusDao;

    @Autowired
    private PdfStamper pdfStamper;


    @GetMapping("/application-1")
    public String showApplication1(Model model) {
        model.addAttribute("app", new Application());
//        model.addAttribute("status", new Status);
        return "application-1";
    }


    @PostMapping("/application-1")
    public String saveRecord(Application app, @RequestParam(name="num_of_copies") String numOfCopies, Model model){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(sessionUser.getId());

//        System.out.println(userDB.getUsername());
        model.addAttribute("copies", numOfCopies);
        app.setUser(userDB);
        appDao.save(app);
    return "redirect:/application-2";
    }


    @GetMapping("/application-2")
    public String showApplication2(Model model) {
        model.addAttribute("record", new Record());
        return "application-2";
    }

    @PostMapping("/application-2")
    public String saveApp2(Record record){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(sessionUser.getId());

        Long appDB_id= 1L;
        Iterable<Application> apps = appDao.findAll();
        for(Application app: apps) {
            if (app.getUser() == userDB) {
               appDB_id = app.getId();
                }
            }

//        System.out.println(appDB_id);
        Application app= appDao.findOne(appDB_id);
        record.setApplication(app);
//        System.out.println(record);
        System.out.println(record.getSex());
       record.setSex(record.getSex());
        app.setRecord(record);
        recordDao.save(record);
      return "redirect:/application-3";
    }

    @GetMapping("/application-3")
    public String showApplication3(Model model) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(sessionUser.getId());
            Application appDB = null;
        Iterable<Application> apps = appDao.findAll();
        for(Application app:apps){
            if(app.getUser() == userDB){
                appDB = app;
            }
        }
            Long recordDB_id= null;
        Iterable<Record> allrecords = recordDao.findAll();
        for(Record record : allrecords){
            if(record.getApplication() == appDB){
                recordDB_id = record.getId();
            }
        }

        System.out.println(appDB);
        System.out.println(recordDB_id);

        Record record = recordDao.findOne(recordDB_id);
        model.addAttribute("record", record);
        return "application-3";
    }

    @PostMapping("/application-3")
    public String saveApp3(Record record) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(sessionUser.getId());
        Application appDB = null;
        Iterable<Application> apps = appDao.findAll();
        for(Application app:apps){
            if(app.getUser() == userDB){
                appDB = app;
            }
        }
        Long recordDB_id= null;
        Iterable<Record> allrecords = recordDao.findAll();
        for(Record record_db : allrecords) {
//            System.out.println(record_db.getApplication());
            if (record_db.getApplication().getId() == appDB.getId()) {
                recordDB_id = record_db.getId();
            }
        }

        Record record1 = recordDao.findOne(recordDB_id);


        record1.setParent1_first_name(record.getParent1_first_name());
        record1.setParent1_mid_name(record.getParent1_mid_name());
        record1.setParent1_last_name(record.getParent1_last_name());
        record1.setParent2_first_name(record.getParent2_first_name());
        record1.setParent2_mid_name(record.getParent2_mid_name());
        record1.setParent2_last_name(record.getParent2_last_name());
        recordDao.save(record1);
        return "redirect:/application-4";
    }

    @GetMapping("/application-4")
    public String showApplication4(Model model) {
        model.addAttribute("mailaddress", new MailingAddress());
        return "application-4";
    }

    @PostMapping("/application-4")
    public String saveApp4(MailingAddress address) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(sessionUser.getId());

        Application appDB = null;
        Iterable<Application> apps = appDao.findAll();
        for(Application app:apps){
            if(app.getUser() == userDB){
                appDB = app;
            }
        }
        Long recordDB_id= null;
        Iterable<Record> allrecords = recordDao.findAll();
        for(Record record_db : allrecords) {
//            System.out.println(record_db.getApplication());
            if (record_db.getApplication().getId() == appDB.getId()) {
                recordDB_id = record_db.getId();
            }
        }


//        User user = userDao.findOne(1L);
        address.setUser(userDB);
        mailDao.save(address);

// -----START OF GETTING FORM FIELDS POPULATED BY DB -------
// passing thru a record and app object - separating thru preparepdf function
        Application app = appDao.findOne(appDB.getId());
        Record record = recordDao.findOne(recordDB_id);
//        User user = userDao.findOne(1L);
//        MailingAddress address1 = mailDao.findOne(1L);

        pdfStamper.preparePdf(record, app, userDB, address);
        return "redirect:/completed-application";
    }

    @GetMapping("/completed-application")
    public String reviewApplication(Model model) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(sessionUser.getId());

        Application appDB = null;
        Iterable<Application> apps = appDao.findAll();
        for(Application app:apps){
            if(app.getUser() == userDB){
                appDB = app;
            }
        }
        Record recordDB= null;
        Iterable<Record> allrecords = recordDao.findAll();
        for(Record record_db : allrecords) {
//            System.out.println(record_db.getApplication());
            if (record_db.getApplication().getId() == appDB.getId()) {
                recordDB = record_db;
            }
        }

        MailingAddress mailingAddress = null;
        Iterable<MailingAddress> addresses = mailDao.findAll();
        for(MailingAddress address: addresses){
            if(recordDB.getApplication() == appDB){
                mailingAddress = address;
            }
        }


        model.addAttribute("app", appDB);
        model.addAttribute("record", recordDB);
        model.addAttribute("mailaddress", mailingAddress);
        return "completed-application";
    }

    @PostMapping("/completed-application")
    public String confirmationOfApplication(){
        return "redirect:/checkout";
    }

    @GetMapping("/checkout")
    public String goToCheckout(){
        return "checkout";
    }

    @GetMapping("/upload")
    public String uploadApplication(Model model) {
//        Application app = appDao.findOne(1L);
//        model.addAttribute("app", app);
        return "upload"; }

    @PostMapping("/upload")
    public String saveFileToDb(@RequestParam(name="urlImg") String url) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(sessionUser.getId());

        Application appDB = null;
        Iterable<Application> apps = appDao.findAll();
        for(Application app:apps){
            if(app.getUser() == userDB){
                appDB = app;
            }
        }

//        appDB = appDao.findOne(appDB.getId());
        appDB.setIdentification_img(url);
//        System.out.println(url);
        appDao.save(appDB);
        return "redirect:/upload";
    }

    @GetMapping("/confirmation")
    public String showConfirmation() {
        return "confirmation";
    }




}
