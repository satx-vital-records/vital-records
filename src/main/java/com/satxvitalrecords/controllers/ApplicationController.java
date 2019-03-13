package com.satxvitalrecords.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
    @GetMapping("/application-1")
    public String showApplication1() {
        return "application-1";
    }

    @GetMapping("/application-2")
    public String showApplication2() {
        return "application-2";
    }

    @GetMapping("/application-3")
    public String showApplication3() {
        return "application-3";
    }

    @GetMapping("/application-4")
    public String showApplication4() {
        return "application-4";
    }
}

