package com.satxvitalrecords.controllers;
import com.satxvitalrecords.models.*;
import com.satxvitalrecords.repositories.*;
import com.satxvitalrecords.services.PdfStamper;
//import com.sun.javaws.security.AppPolicy;
import com.satxvitalrecords.services.TwilioService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.sendgrid.*;

import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Value("${file-upload-path}")
    private String uploadPath;

//    @Autowired
//    private GooglePlacesTest googlePlace;


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


    @PostMapping("/application-1")
    public String saveRecord(@ModelAttribute Application app, @RequestParam(name="record_type") String record_type, @RequestParam(name="num_of_copies") String numOfCopies, Model model){


        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(sessionUser.getId());


        app.setUser(userDB);
        app.setStatus(statusDao.findOne(100L));
        appDao.save(app);

        model.addAttribute("record_type", record_type);
        model.addAttribute("copies", numOfCopies);
        model.addAttribute("app", app);

    return "redirect:/application-2";
    }


    @GetMapping("/application-2")
    public String showApplication2(Model model) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(sessionUser.getId());
        Application appDB = null;
        Iterable<Application> apps = appDao.findAll();
        for(Application app:apps){
            if(app.getUser() == userDB){
                appDB = app;
            }
        }
        model.addAttribute("app", appDB);
        model.addAttribute("record", new Record());
        return "application-2";
    }

    @PostMapping("/application-2")
    public String saveApp2(Record record, @RequestParam(name="dob_dod") String dob_dod){
      SimpleDateFormat formatter;

      User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(sessionUser.getId());

        Long appDB_id= 1L;
        Iterable<Application> apps = appDao.findAll();
        for(Application app: apps) {
            if (app.getUser() == userDB) {
               appDB_id = app.getId();
                }
            }

        Application app= appDao.findOne(appDB_id);
        record.setApplication(app);

        if(app.getRecord_type().equals("Birth")){

          formatter = new SimpleDateFormat("yyyy-MM-dd");
          try {

            Date dob_date = formatter.parse(dob_dod);
            record.setDate_of_birth(dob_date);

          } catch (ParseException e) {
            e.printStackTrace();
          }
        } else if(app.getRecord_type().equals("Death")){
          formatter = new SimpleDateFormat("yyyy-MM-dd");
          try {

            Date dod_date = formatter.parse(dob_dod);
            record.setDate_of_death(dod_date);

          } catch (ParseException e) {
            e.printStackTrace();
          }
        }


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

        address.setUser(userDB);
        mailDao.save(address);

// -----START OF GETTING FORM FIELDS POPULATED BY DB -------
// passing thru a record and app object - separating thru preparepdf function
        Application app = appDao.findOne(appDB.getId());
        Record record = recordDao.findOne(recordDB_id);
        long millis = System.currentTimeMillis();
        pdfStamper.preparePdf(record, app, userDB, address, millis);
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
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(sessionUser.getId());

        Application appDB = null;
        Iterable<Application> apps = appDao.findAll();
        for(Application app:apps){
            if(app.getUser() == userDB){
                appDB = app;
            }
        }

        appDB.setStatus(statusDao.findOne(200L));

      Date date = new Date();
      appDB.getRecord().setDate_of_request(date);

      appDao.save(appDB);
        return "redirect:/checkout";
    }

    @GetMapping("/checkout")
    public String goToCheckout(Model model){

        model.addAttribute("file", pdfStamper.DEST);
        System.out.println(pdfStamper.DEST);
        return "checkout";
    }

    @Value("${SENDGRID_API_KEY}") String sendGridKey;
    @Value("${TWILIO_ACCOUNT_SID}") String ACCOUNT_SID;
    @Value("${TWILIO_AUTH_TOKEN}") String AUTH_TOKEN;
    @PostMapping("/checkout")
    public String sendEmail() throws IOException{
        System.out.println("sendEmail function triggered!");
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(sessionUser.getId());

        Email from = new Email("admin@satxvitalrecords.com");
        String subject = "Vital Records Application Submitted";
        Email to = new Email(userDB.getEmail());
        Content content = new Content("text/plain", "Application successfully sent! \nThank you for your application, we are locating your record and will notify you when it is available to be be picked up from the City Clerk's Office. \nPlease complete the next steps to ensure your application process is successfully completed. \n- San Antonio Vital Records");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
        } catch (IOException ex) {
            System.out.println("email sending failed");
            throw ex;
        }



        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+1"+ userDB.getPhone_num()), // to
                        new PhoneNumber("+12109439303"), // from
                        "Your application was successfully sent! \n- SATX Vital Records").create();


        System.out.println("this is in the sendEmail method on the app controller" + message.getSid());

        return "redirect:/checkout";
    }


    @GetMapping("/upload")
    public String uploadApplication(Model model) {

        return "upload"; }

    @PostMapping("/upload")
    public String saveFileToDb(@RequestParam(name="urlImg1") String url, @RequestParam(name="urlImg2") String url2) {
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
        appDB.setForm_img(url2);
//        System.out.println(url);

//        Date date = new Date();
//        System.out.println(date);

//        appDB.getRecord().setDate_of_request(date);
        appDB.setStatus(statusDao.findOne(300L));
        appDao.save(appDB);
        return "redirect:/charge";
    }

    @GetMapping("/confirmation")
    public String showConfirmation() {
        return "confirmation";
    }






}
