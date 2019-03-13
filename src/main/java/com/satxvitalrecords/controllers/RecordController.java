package com.satxvitalrecords.controllers;

import com.satxvitalrecords.models.Application;
import com.satxvitalrecords.repositories.ApplicationRepo;
import com.satxvitalrecords.repositories.RecordRepo;
import com.satxvitalrecords.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RecordController {

  @Autowired
  private RecordRepo recordDao;

  @Autowired
  private ApplicationRepo applicationDao;

  @Autowired
  private UserRepo userDao;


}
