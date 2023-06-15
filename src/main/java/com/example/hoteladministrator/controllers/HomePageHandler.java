package com.example.hoteladministrator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePageHandler {
    @GetMapping
    public String homePageRedirect(){
        return "homePage";
    }
}
