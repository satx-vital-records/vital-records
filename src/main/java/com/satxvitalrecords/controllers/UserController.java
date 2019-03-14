package com.satxvitalrecords.controllers;

import com.satxvitalrecords.models.User;
import com.satxvitalrecords.models.Users;
import org.springframework.stereotype.Controller;
import com.satxvitalrecords.repositories.AddressRepo;
import com.satxvitalrecords.repositories.ApplicationRepo;
import com.satxvitalrecords.repositories.UserRepo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

  private Users users;
//  private PasswordEncoder passwordEncoder;


  @Autowired
  private UserRepo userDao;

  @Autowired
  private AddressRepo addressDao;

  @Autowired
  private ApplicationRepo applicationDao;

//--- DO NOT UNCOMMENT UNTIL READY TO ADD SECURITY DEPENDENCY! ---

//  private Users users;
//  private PasswordEncoder passwordEncoder;

//  public UserController(Users users, PasswordEncoder passwordEncoder) {
//    this.users = users;
//    this.passwordEncoder = passwordEncoder;
//  }
//  -----------------------------------------------------------------


  @GetMapping("/register")
  public String showRegisterForm(Model model){
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping("/register")
  public String saveUser(@ModelAttribute User user){
//    String hash = passwordEncoder.encode(user.getPassword());
//    user.setPassword(hash);
    System.out.println(user.getFirst_name());
    System.out.println(user.getEmail());
    System.out.println(user.getId());
    System.out.println(user.getLast_name());
    System.out.println(user.getPassword());
    System.out.println(user.getPhone_num());
    System.out.println(user.getRole());
    System.out.println(user.getUsername());
    userDao.save(user);
    return "redirect:login";
  }

}
