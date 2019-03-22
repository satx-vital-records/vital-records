package com.satxvitalrecords.controllers;

import com.satxvitalrecords.models.User;
import com.satxvitalrecords.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MiddlewareController {

  @Autowired
  private UserRepo userDao;

  @GetMapping("/role-redirect")
  public String roleRedirect(){
    User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User userDB = userDao.findOne(sessionUser.getId());

    if(userDB.getRole()== 1){
      return "redirect:/app-index";
    }
    return "index";
  }


}
