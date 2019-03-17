package com.satxvitalrecords.controllers;
import com.satxvitalrecords.models.Application;
import com.satxvitalrecords.models.MailingAddress;
import com.satxvitalrecords.models.Record;
import com.satxvitalrecords.models.User;
import com.satxvitalrecords.repositories.AddressRepo;
import com.satxvitalrecords.repositories.ApplicationRepo;
import com.satxvitalrecords.repositories.RecordRepo;
import com.satxvitalrecords.repositories.UserRepo;
import com.satxvitalrecords.services.PdfStamper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    private PdfStamper pdfStamper;

//    @GetMapping("/application-1")
//    public String showApplication1(Model model) {
//      model.addAttribute("record", new Record());
//      return "application-2";
//    }

//    User user = new User();

    @GetMapping("/application-1")
    public String showApplication1(Model model) {
        model.addAttribute("app", new Application());
        return "application-1";
    }

//    @PostMapping("/application-1")
//    public String saveRecord(@ModelAttribute Record record){
//        recordDao.save(record);
//        return "redirect:/application-2";
//    }

    @PostMapping("/application-1")
    public String saveRecord(Application app){
        User user = userDao.findOne(1L);
        app.setUser(user);
        appDao.save(app);
    return "redirect:/application-2";
    }

//    @GetMapping("/application-2")
//    public String showApplication2() {
//        return "application-3";
//    }

    @GetMapping("/application-2")
    public String showApplication2(Model model) {
        model.addAttribute("record", new Record());
        return "application-2";
    }

    @PostMapping("/application-2")
    public String saveApp2(Record record){
        Application app= appDao.findOne(1L);
        record.setApplication(app);
        System.out.println(record);
        System.out.println(app);
        recordDao.save(record);
      return "redirect:/application-3";
    }

    @GetMapping("/application-3")
    public String showApplication3(Model model) {
        Record record = recordDao.findOne(1L);
        model.addAttribute("record", record);

        //maybe get userid by session using spring sessions?
//        User usersInDb = userDao.findOne(sessionUser.getId());
        return "application-3";
    }

    @PostMapping("/application-3")
    public String saveApp3(Record record) {
        Record record1 = recordDao.findOne(1L);
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
//        User user = userDao.findOne(1L);
//        address.setUser(user);
//        mailDao.save(address);

// -----START OF GETTING FORM FIELDS POPULATED BY DB -------
//        String name = recordDao.findOne(1L).getFirst_name();
        pdfStamper.preparePdf(recordDao.findOne(1L));

        return "redirect:/payment";
    }

    @GetMapping("/payment")
    public String showPayments() {
        return "checkout";
    }

//    @GetMapping("/completed-application")
//    public String completedApplication(){
//        return "completed-application";
//    }

    @GetMapping("/upload")
    public String uploadApplication() { return "upload"; }

}
