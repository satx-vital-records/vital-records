package com.satxvitalrecords.controllers;

import com.satxvitalrecords.models.User;
import com.satxvitalrecords.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import com.satxvitalrecords.repositories.AddressRepo;
import com.satxvitalrecords.repositories.ApplicationRepo;
import com.satxvitalrecords.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class UserController {

  @Autowired
  private UserRepo userDao;

  @Autowired
  private AddressRepo addressDao;

  @Autowired
  private ApplicationRepo applicationDao;

//--- DO NOT UNCOMMENT UNTIL READY TO ADD SECURITY DEPENDENCY! ---

  private Users users;
  private PasswordEncoder passwordEncoder;

  public UserController(Users users, PasswordEncoder passwordEncoder) {
    this.users = users;
    this.passwordEncoder = passwordEncoder;
  }

//  -----------------------------------------------------------------



  @GetMapping("/")
  public String showRegisterForm(Model model){
//    User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    User userDB = userDao.findOne(sessionUser.getId());
//    User userobject =null;
//    Iterable<User> allusers = userDao.findAll();
//    for(User user:allusers){
//      if(sessionUser == userDB){
//        userobject=user;
//      }
//      model.addAttribute("user", userobject);
//      return "index";
//    }
    model.addAttribute("user", new User());
    return "index";
  }

  @PostMapping("/")
  public String saveUser(@ModelAttribute User user){
    Iterable<User> users = userDao.findAll();
    for(User user_db: users) {
      if (user.getEmail() == user_db.getEmail() || user.getUsername() == user_db.getUsername()) {
        return "redirect:login";
      }
    }
    String hash = passwordEncoder.encode(user.getPassword());
    user.setPassword(hash);
    userDao.save(user);
    return "redirect:login";
  }



}
