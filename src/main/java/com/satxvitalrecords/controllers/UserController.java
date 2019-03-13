package com.satxvitalrecords.controllers;

import com.satxvitalrecords.models.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
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
//    users.save(user);
    return "redirect:login";
  }

}
