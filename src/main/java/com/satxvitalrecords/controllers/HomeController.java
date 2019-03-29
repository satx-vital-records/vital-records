package com.satxvitalrecords.controllers;

import com.satxvitalrecords.models.Application;
import com.satxvitalrecords.models.Record;
import com.satxvitalrecords.models.User;
import com.satxvitalrecords.repositories.ApplicationRepo;
import com.satxvitalrecords.repositories.RecordRepo;
import com.satxvitalrecords.repositories.StatusRepo;
import com.satxvitalrecords.repositories.StatusRepo;
import com.satxvitalrecords.repositories.UserRepo;
import com.sendgrid.*;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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


  @GetMapping("/app-index")
  public String viewAllApps(Model model) {

    User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User userDB = userDao.findOne(sessionUser.getId());

    model.addAttribute("user", userDB);

    Iterable<Application> allapps = appDao.findAll();
    List<Application> viewapps = new ArrayList<>();
    for(Application app:allapps){
      if((app.getStatus()== null) || app.getStatus().getId()== 100 || (app.getRecord()== null)){
//        appDao.delete(app.getId());
        continue;
      } else {
        viewapps.add(app);
      }
    }

    int inprogress = numberOfApps(viewapps, "In Progress");
    int need_docs = numberOfApps(viewapps, "Need Uploads");
    int pending_review = numberOfApps(viewapps, "Pending Review");
    int approved = numberOfApps(viewapps, "Approved");
    int mailed = numberOfApps(viewapps, "Mailed");
    int pickedup = numberOfApps(viewapps, "Picked-up");
//        System.out.println(inprogress);

    model.addAttribute("inprogress", inprogress);
    model.addAttribute("pendingreview", pending_review);
    model.addAttribute("needdocs", need_docs);
    model.addAttribute("approved", approved);
    model.addAttribute("mailed", mailed);
    model.addAttribute("pickedup", pickedup);
    model.addAttribute("apps", viewapps);


    return "app-index";
  }


//    @PostMapping("/app-index")
//    public String leaveComment(@ModelAttribute Application app) {
//        appDao.save(app);
//        return "redirect: /app-index";
//    }


    @GetMapping("/app-view/{id}")
    public String viewAllApps(@PathVariable long id, Model model){
        Application app = appDao.findOne(id);


        int inprogress = numberOfApps(appDao.findAll(), "In Progress");
        int need_docs = numberOfApps(appDao.findAll(), "Need Uploads");
        int pending_review = numberOfApps(appDao.findAll(), "Pending Review");
        int approved = numberOfApps(appDao.findAll(), "Approved");
        int mailed = numberOfApps(appDao.findAll(), "Mailed");
        int pickedup = numberOfApps(appDao.findAll(), "Picked-up");
//        System.out.println(inprogress);

        model.addAttribute("apps", appDao.findAll());
        model.addAttribute("inprogress", inprogress);
        model.addAttribute("pendingreview", pending_review);
        model.addAttribute("needdocs", need_docs);
        model.addAttribute("approved", approved);
        model.addAttribute("mailed", mailed);
        model.addAttribute("pickedup", pickedup);



        model.addAttribute("app", app);
        return "app-view";
    }


  @Value("${SENDGRID_API_KEY}") String sendGridKey;
  @Value("${TWILIO_ACCOUNT_SID}") String ACCOUNT_SID;
  @Value("${TWILIO_AUTH_TOKEN}") String AUTH_TOKEN;
    @PostMapping("/app-view/{id}")
    public String leaveComment(@PathVariable long id, @RequestParam(name="update_status") long status) {
        Application app = appDao.findOne(id);
        app.setStatus(statusDao.findOne(status));
        appDao.save(app);

//        if(app.getStatus().getId() == 400) {
//          Email from = new Email("admin@satxvitalrecords.com");
//          String subject = "Vital Records Application Submitted";
//          Email to = new Email(app.getUser().getEmail());
//          Content content = new Content("text/plain", "Thank you for your patience, we have reviewed and approved your record order. We have located and pulled your record. You are now able to pick up your vital record from the City Clerk's Office. \nPlease ensure to bring original copies of uploaded documents to ensure proper identification to release your order. \n Feel free to contact us with any questions or concerns. Thank you again for using San Antonio Vital Records Express Service. \n- San Antonio Vital Records");
//          Mail mail = new Mail(from, subject, to, content);
//
//          SendGrid sg = new SendGrid(sendGridKey);
//          Request request = new Request();
//          try {
//            request.setMethod(Method.POST);
//            request.setEndpoint("mail/send");
//            request.setBody(mail.build());
//            Response response = sg.api(request);
////            System.out.println(response.getStatusCode());
////            System.out.println(response.getBody());
////            System.out.println(response.getHeaders());
//
//            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//            Message message = Message
//                    .creator(new PhoneNumber(app.getUser().getPhone_num()), // to
//                            new PhoneNumber("+12109439303"), // from
//                            "Your vital record is now available for pickup! Don't forget to bring original documents that were uploaded in application to release vital record order. \n- SATX Vital Records").create();
//
//            System.out.println(message.getSid());
//          } catch (IOException e) {
//            e.printStackTrace();
//            return "redirect:/app-index";
//          }
//
//        }


//        Date date = new Date();
//        DateFormat dateFormat = new SimpleDateFormat("YY/MM/DD hh:mm:ss");
//        dateFormat.format(date);
//        app.setComments(comments);
//        Date date = new Date();
//        String strDateFormat = "YY/MM/DD hh:mm:ss";
//        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
//       dateFormat.format(date);
//        app.setComment_dateTime(date);
//        System.out.println("This is the status id we're getting: " + status);
        return "redirect:/app-index";
    }

    @GetMapping("/bc-info")
    public String showBcInfoPage(Model model) {
        model.addAttribute("app", new Application());
        return "bc-info";
    }

    @PostMapping("/bc-info")
    public String birthRecord(Application app, @RequestParam(name="RT") String record_type, Model model){
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
    public String deathRecord(Application app, @RequestParam(name="RT") String record_type){
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userDao.findOne(sessionUser.getId());
        app.setUser(userDB);
        app.setRecord_type(record_type);
        app.setStatus(statusDao.findOne(100L));
        appDao.save(app);
        return "redirect:/application-1";
    }

    @GetMapping("/map")
    public String showMap() {
        return "map";
    }

    @GetMapping("/login-form")
    public String showForm() {
        return "login-form";
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

    @GetMapping("/about-us")
    public String aboutUs() { return "about-us"; }
}
