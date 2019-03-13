package com.satxvitalrecords.controllers;

import com.satxvitalrecords.models.Record;
import com.satxvitalrecords.repositories.ApplicationRepo;
import com.satxvitalrecords.repositories.RecordRepo;
import com.satxvitalrecords.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationController {

  @Autowired
  private ApplicationRepo appDao;

  @Autowired
  private UserRepo userDao;

  @Autowired
  private RecordRepo recordDao;
}
